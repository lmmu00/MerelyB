package com.merelyb.service.redis;

import com.merelyb.bean.dataConf.RedisConf;
import com.merelyb.utils.database.RedisUtils;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @项目: Merelyb
 * @包: com.merelyb.service.redis
 * @作者: LiM
 * @创建时间: 2018-09-04 20:43
 * @Description: ${Description}
 */
public class RedisOperationService {

    private RedisConfService redisConfService;

    private RedisUtils redisUtils;

    private boolean bHasRedis = false;

    /**
     * 初始化
     *
     * @throws Exception
     */
    public RedisOperationService() throws Exception {
        redisConfService = new RedisConfService();
        RedisConf redisConf = new RedisConf();
        redisConf.setIsDelete((byte) 0);
        List<RedisConf> redisConfList = redisConfService.select(redisConf);
        if (redisConfList != null && redisConfList.size() > 0) {
            List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
            for (RedisConf redis : redisConfList
                    ) {
                Pattern patternUrl = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                Pattern patternIP = Pattern.compile("(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))");
                if (!patternUrl.matcher(redis.getAddIP()).find() && !patternIP.matcher(redis.getAddIP()).find())
                    continue;
                if (redis.getPort() == null) continue;
                bHasRedis = true;
                JedisShardInfo jedisShardInfo = new JedisShardInfo(redis.getAddIP(), Integer.parseInt(String.valueOf(redis.getPort())));
                if (redis.getAuthPwd() != null) jedisShardInfo.setPassword(redis.getAuthPwd());
                jedisShardInfoList.add(jedisShardInfo);
            }
            if (bHasRedis) {
                redisUtils = new RedisUtils(jedisShardInfoList);
            }
        }
    }

    /**
     * token 添加到redis
     *
     * @param sValue
     * @param sToken
     * @return
     */
    public boolean addNewToken(String sValue, String sToken) {
        if (!bHasRedis) return false;
        return redisUtils.add(sToken, sValue);
    }

    /**
     * 通过token获取账户信息
     *
     * @param sToken
     * @return
     */
    public String getAccFromToken(String sToken) {
        if (!bHasRedis) return null;
        return redisUtils.get(sToken, String.class);
    }

    /**
     * 设置token超时时间
     *
     * @param sToken
     * @param lTime
     * @return
     */
    public Long setTokenTime(String sToken, int lTime) {
        if (!bHasRedis) return -1L;
        return redisUtils.expire(sToken, lTime);
    }

    /**
     * 验证token是否存在
     *
     * @param sToken
     * @return
     */
    public boolean exitToken(String sToken) {
        if (!bHasRedis) return false;
        return redisUtils.exist(sToken);
    }

    /**
     * 销毁
     */
    public void destroy() {
        if (redisUtils != null)
            redisUtils.destroy();
        redisConfService = null;
    }
}
