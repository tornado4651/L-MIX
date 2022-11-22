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
//        userMapper.delete(new QueryWrapper<User>().eq("username","admin"));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
//        User user = User.builder()
//                .nickname("管理员")
//                .username("admin")
//                .password("123456")
//                .telephone("13888888888")
//                .gender((byte) 1)
//                .avatar(null)
//                .birthday(dateFormat.parse("1996-08"))
//                .build();
//        userMapper.insert(user);
//        System.out.println("插入完毕！");
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<>());
        System.out.println(userList);
    }


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void testRedis(){
        Object ttttt = redisTemplate.opsForValue().get("ttttt");
        System.out.println(ttttt);
    }
}
