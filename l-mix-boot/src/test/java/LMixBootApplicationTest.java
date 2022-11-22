import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tornado4651.lmix.boot.LMixBootApplication;
import com.tornado4651.lmix.boot.domain.User;
import com.tornado4651.lmix.boot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.util.List;
import java.util.Set;


@SpringBootTest(classes = {LMixBootApplication.class})
public class LMixBootApplicationTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试mybatis
     * @throws ParseException
     */
    @Test
    public void testMyBatis() {
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<>());
        System.out.println(userList);
    }


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void testRedis(){
        Object ttttt = redisTemplate.opsForValue().get("ttttt");
        System.out.println(ttttt);
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
    }
}
