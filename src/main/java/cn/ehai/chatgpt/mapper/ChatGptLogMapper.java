package cn.ehai.chatgpt.mapper;

import cn.ehai.chatgpt.domain.ChatGptLog;
import org.apache.ibatis.annotations.Mapper;


//@Mapper
public interface ChatGptLogMapper {
    int insert(ChatGptLog chatgptlog);

}