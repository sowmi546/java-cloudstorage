package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userid}")
    List<Credentials> getCredentials(int userid);

    @Insert("INSERT INTO CREDENTIALS (url,username,key, password, userid) VALUES(#{url},#{username},#{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredentials(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialId=#{credentialId}")
    int updateCredentials(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId=#{credentialId}")
    int deleteCredentials(int credentialId);


}
