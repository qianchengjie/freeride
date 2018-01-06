package com.hdu.freeride.repository;

import com.hdu.freeride.entity.UserRoleStrokeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 18:51
 */
@Repository
public interface UserRoleStrokeRelationRepository extends JpaRepository<UserRoleStrokeRelation, Integer> {

    @Query("select strokeId from UserRoleStrokeRelation where userId=:userId and roleId=:roleId")
    public List<Integer> findAllStrokeIdByUserIdAndRoleId(@Param("userId") int userId,@Param("roleId") int roleId);

    @Query("select strokeId from UserRoleStrokeRelation where userId=:userId")
    public List<Integer> findAllStrokeIdByUserId(@Param("userId") int userId);

    @Query("select strokeId from UserRoleStrokeRelation where roleId=:roleId")
    public List<Integer> findAllStrokeIdByRoleId(@Param("roleId") int roleId);

    public UserRoleStrokeRelation findByUserIdAndRoleIdAndStrokeId(Integer userId, Integer roleId, Integer strokeId);


    public List<UserRoleStrokeRelation> findAllByStrokeId(Integer strokeId);

}
