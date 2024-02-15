-- 设置用户访问频率限制参数
local username = KEYS[1]
local timeWindow = tonumber(ARGV[1])    -- 时间窗口 单位：秒

-- 构造 Redis 中存储用户访问次数的键值名称
local accessKey = "short-link:user-flow-risk-control:" .. username

-- 原子递增访问次数，并获取递增后的指
local currentAccessCount = redis.call("INCR", accessKey)

-- 设置键值的过期时间
redis.call("EXPIRE", accessKey, timeWindow)

-- 返回当前访问次数
return currentAccessCount