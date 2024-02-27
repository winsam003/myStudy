package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.UserDTO;

public interface UserMapper {

//	@Select("select * from user")
	public List<UserDTO> userList();
	
}
