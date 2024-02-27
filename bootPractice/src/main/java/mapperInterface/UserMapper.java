package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.UserDTO;

public interface UserMapper {

	// userList
	public List<UserDTO> userList();
	
	
	// userDetail
	@Select("select * from user where id = #{id}")
	public UserDTO userDetail(String id);
	
}
