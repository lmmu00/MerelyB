package com.merelyb.utils.database;

import com.google.gson.reflect.TypeToken;
import com.merelyb.utils.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @项目: Merelyb
 * @包: com.merelyb.utils.database
 * @作者: LiM
 * @创建时间: 2018-08-23 18:19
 * @Description: Redis链接
 */
public class RedisUtils {

    private JedisPoolConfig jedisPoolConfig;

    private ShardedJedis shardedJedis;//切片额客户端连接

    private ShardedJedisPool shardedJedisPool;//切片连接池

    /**
     * 初始化配置
     */
    private void initRedisConfig(){
        jedisPoolConfig = new JedisPoolConfig();
        //最大活动对象数
        jedisPoolConfig.setMaxTotal(1000);
        //最大能够保持idel状态的对象数
        jedisPoolConfig.setMaxIdle(100);
        //最小能够保持idel状态的对象数
        jedisPoolConfig.setMinIdle(50);
        //当池内没有返回对象时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(1000L);
        //当调用borrow Object方法时，是否进行有效性检查
        jedisPoolConfig.setTestOnBorrow(true);
        //当调用return Object方法时，是否进行有效性检查
        jedisPoolConfig.setTestOnReturn(true);
        //#“空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(1000L);
        //向调用者输出“链接”对象时，是否检测它的空闲超时
        jedisPoolConfig.setTestWhileIdle(true);
        //对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
        jedisPoolConfig.setNumTestsPerEvictionRun(50);
    }

    /**
     * 初始化切片连接
     * @param jedisShardInfoList
     */
    public RedisUtils(List<JedisShardInfo> jedisShardInfoList){
        initRedisConfig();
        shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);
        shardedJedis = shardedJedisPool.getResource();
    }

    /**
     * 检测Key 是否存在
     * @param sKey
     * @return
     */
    public boolean exist(String sKey){
        return shardedJedis.exists(sKey);
    }

    /**
     * 删除key
     * @param sKey
     * @return
     */
    public Long del(String sKey){
        return shardedJedis.del(sKey);
    }

    /**
     * 获取所有的key
     * @return
     */
    public List<String> getAllKey(){
        List<String> keyList = new ArrayList<>();
        Iterator<Jedis> jedisIterator = shardedJedis.getAllShards().iterator() ;
        while (jedisIterator.hasNext()){
            Jedis jedis = jedisIterator.next();
            Iterator<String> it=jedis.keys("*").iterator() ;
            while(it.hasNext()){
                String key = it.next();
                keyList.add(key);
            }
        }
        return keyList;
    }

    /**
     * 设置Key 失效时间
     * @param sKey
     * @param iSecond
     * @return
     */
    public Long expire(String sKey, int iSecond){
        return shardedJedis.expire(sKey, iSecond);
    }

    /**
     * 查看Key的剩余生存时间
     * @param sKey
     * @return
     */
    public Long getKeyLimit(String sKey){
        Jedis jedis = shardedJedis.getShard(sKey);
        if(jedis == null) return -1L;
        return jedis.ttl(sKey);
    }

    /**
     * 移除Key的生存时间
     * @param sKey
     * @return
     */
    public Long removeKeyLimit(String sKey){
        Jedis jedis = shardedJedis.getShard(sKey);
        if(jedis == null) return -1L;
        return jedis.persist(sKey);
    }

    /**
     * 获取Key的类型
     * @param sKey
     * @return
     */
    public String getKeyValueType(String sKey){
        Jedis jedis = shardedJedis.getShard(sKey);
        if(jedis == null) return "";
        return jedis.type(sKey);
    }

    /**
     * 新增
     * @param sKey
     * @param sValue
     */
    private boolean add(String sKey, String sValue){
        if(StringUtils.isBlank(sKey) || StringUtils.isBlank(sValue)) return false;
        if(shardedJedis.exists(sKey)) return false;
        boolean bReturn = shardedJedis.set(sKey, sValue).toLowerCase().equals("ok");
        if (bReturn == false) return false;
        return true;
    }

    /**
     * 获取数据
     * @param sKey
     * @return
     */
    private String get(String sKey){
        if(shardedJedis.type(sKey) .toLowerCase().equals("string")) {
            return shardedJedis.get(sKey);
        }else return null;
    }

    /**
     * 新增
     * @param sKey
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean add(String sKey, T obj){
        if(obj == null) return false;
        String sValue = JsonUtils.obj2Json(obj);
        return add(sKey, sValue);
    }

    /**
     * 获取
     * @param sKey
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T get(String sKey, Class<T> tClass){
        if (tClass == null) return null;
        String sValue = get(sKey);
        if(sValue == null) return null;
        return JsonUtils.json2Obj(sValue, tClass);
    }

    /**
     * 新增
     * @param sKey
     * @param tList
     * @param <T>
     * @return
     */
    public <T> boolean add(String sKey, List<T> tList){
        String sValue = JsonUtils.objs2Json(tList);
        return add(sKey, sValue);
    }

    /**
     * 获取
     * @param sKey
     * @param <T>
     * @return
     */
    public <T> List<T> getStringList(String sKey){
        String sValue = get(sKey);
        if(sValue == null) return null;
        return JsonUtils.json2Objs(sValue, new TypeToken<List<T>>(){});
    }

    /**
     * 新增list中的值
     * @param sKey
     * @param sValue
     * @return
     */
    private boolean listAdd(String sKey, String sValue){
        if(StringUtils.isBlank(sKey) || StringUtils.isBlank(sValue)) return false;
        shardedJedis.lpush(sKey, sValue);
        return true;
    }

    /**
     * 批量添加list数据
     * @param sKey
     * @param tList
     * @param <T>
     */
    public<T> boolean listAdd(String sKey, List<T> tList){
        boolean bReturn = true;
        for (int i = 0; i < tList.size(); i++) {
            T obj = tList.get(i);
            bReturn =listAdd(sKey, JsonUtils.obj2Json(obj));
            if (bReturn == false) break;
        }
        if(bReturn == false) {
            shardedJedis.del(sKey);
            return false;
        }
        return bReturn;

    }

    /**
     *
     * @param sKey
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean listAdd(String sKey, T obj){
        if(obj == null) return false;
        String sValue = JsonUtils.obj2Json(obj);
        return listAdd(sKey, sValue);
    }

    /**
     * 返回list
     * @param sKey
     * @return
     */
    public List<String> listGet(String sKey){
        return shardedJedis.lrange(sKey, 0, 01);
    }

    /**
     * 返回key的值
     * @param sKey
     * @param tClass
     * @param <T>
     * @return
     */
    public<T> List<T> listGet(String sKey, Class<T> tClass){
        List<String> valueList = listGet(sKey);
        List<T> tList = new ArrayList<>();
        for (int i = 0; i < valueList.size(); i++) {
            T obj = JsonUtils.json2Obj(valueList.get(i), tClass);
            tList.add(obj);
        }
        return tList;
    }

    /**
     * 获取数据长度
     * @param sKey
     * @return
     */
    public Long listLength(String sKey){
        return shardedJedis.llen(sKey);
    }

    /**
     * 获取指定位置的值
     * @param sKey
     * @param iIndex
     * @return
     */
    private String listGet(String sKey, int iIndex){
        return shardedJedis.lindex(sKey, iIndex);
    }

    /**
     * 获取指定位置的值
     * @param sKey
     * @param iIndex
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T listGet(String sKey, int iIndex, Class<T> tClass){
        String sValue = listGet(sKey, iIndex);
        return JsonUtils.json2Obj(sValue, tClass);
    }

    /**
     * 修改指定位置的值
     * @param sKey
     * @param iIndex
     * @param sValue
     * @return
     */
    private boolean listModify(String sKey, int iIndex, String sValue){
        return shardedJedis.lset(sKey, iIndex, sValue).toLowerCase().equals("ok");
    }

    /**
     * 修改指定位置的值
     * @param sKey
     * @param iIndex
     * @param obj
     * @param <T>
     * @return
     */
    public<T> boolean listModify(String sKey, int iIndex, T obj){
        String sValue = JsonUtils.obj2Json(obj);
        return listModify(sKey, iIndex, sValue);
    }

    /**
     * 删除数据
     * @param sKey
     * @param sValue
     * @return
     */
    private boolean listDel(String sKey, String sValue){
        List<String> valueList = shardedJedis.lrange(sKey, 0, -1);
        for (int i = valueList.size() -1; i >0 ; i--) {
            if(valueList.get(i).equals(sValue)) valueList.remove(i);
        }
        shardedJedis.del(sKey);
        return listAdd(sKey, valueList);
    }

    /**
     *
     * @param sKey
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean listDelAll(String sKey, T obj){
        String sValue = JsonUtils.obj2Json(obj);
        return listDel(sKey, sValue);
    }

    /**
     * 删除首个
     * @param sKey
     * @return
     */
    public boolean listDelFirst(String sKey){
        return shardedJedis.lpop(sKey).toLowerCase().equals("ok");
    }

    /**
     * 删除最后一个
     * @param sKey
     * @return
     */
    public boolean listDelLast(String sKey){
        return shardedJedis.rpop(sKey).toLowerCase().equals("ok");
    }

    /**
     * 添加hash数据
     * @param sKey
     * @param map
     * @return
     */
    public<T> boolean hashAdd(String sKey, Map<String, T> map){
        Map<String, String> mValue = new HashMap<>();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String sMKey = iterator.next();
            mValue.put(sMKey, JsonUtils.obj2Json(map.get(sMKey)));
        }
        return shardedJedis.hmset(sKey, mValue).toLowerCase().equals("ok");
    }

    /**
     * 追加hash 数据
     * @param sKey
     * @param sMapKey
     * @param obj
     * @param <T>
     * @return
     */
    public <T> boolean hashAdd(String sKey, String sMapKey, T obj){
        return shardedJedis.hset(sKey, sMapKey, JsonUtils.obj2Json(obj)) > 0;
    }

    /**
     * 获取map所有key
     * @param sKey
     * @return
     */
    public List<String> hashGetAllMapKeys(String sKey){
        List<String> keyList = new ArrayList<>();
        Set<String> keySets = shardedJedis.hkeys(sKey);
        while (keySets.iterator().hasNext()){
            keyList.add(keySets.iterator().next());
        }
        return keyList;
    }

    /**
     * 获取map的value
     * @param sKey
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> hashGetAllVals(String sKey, Class<T> tClass){
        List<T> valList = new ArrayList<>();
        List<String> valSets = shardedJedis.hvals(sKey);
        for (int i = 0; i < valSets.size(); i++) {
            valList.add(JsonUtils.json2Obj(valSets.get(i), tClass));
        }
        return valList;

    }

    /**
     * 获取值
     * @param sKey
     * @param sMapKey
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T hashGetValue(String sKey, String sMapKey, Class<T> tClass){
        return JsonUtils.json2Obj(shardedJedis.hget(sKey, sMapKey), tClass);
    }

    /**
     * 查看map key是否存在
     * @param sKey
     * @param sMapKey
     * @return
     */
    public boolean hashExistMapKey(String sKey, String sMapKey){
        return shardedJedis.hexists(sKey, sMapKey);
    }

    /**
     * 移除map中的值
     * @param sKey
     * @param sMapKey
     * @return
     */
    public boolean hashDel(String sKey, String sMapKey){
        return shardedJedis.hdel(sKey, sMapKey) > 0;
    }



    private void closeRedis(){
        if(shardedJedis != null){
            shardedJedis.close();
        }
        if(shardedJedisPool != null){
            shardedJedisPool.close();
        }
    }

    public void destroy(){
        closeRedis();
    }


}
