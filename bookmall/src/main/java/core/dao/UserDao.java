package core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import core.domain.User;

@Component
public interface UserDao {

	@Insert(value = "insert into user values(#{id},#{username},#{password},#{phone},#{cellphone},#{email},#{address})")
	public void addUser(User user) throws Exception;

	@Select(value = "select * from user where id=#{id}")
	public User findUserById(String id) throws Exception;
	
	//根据用户user的id获得订单orders的id
	@Select(value = "select orders.id from user,orders where user.id=#{id} and orders.user_id=user.id")
	public List<String> genOrdersIdByUser(@Param("id") String id) throws Exception;

	/*
	 * 多参数查询需要在其中使用@Param()注解来协助。
	 */
	@Select(value = "select * from user where username=#{username} and password=#{password}")
	public User findUser(@Param("username") String username, @Param("password") String password) throws Exception;

}