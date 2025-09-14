package com.jxd.stuManger.dao;

import com.jxd.stuManger.model.College;
import java.util.*;

/**
 * @author 何硕
 * @version 1.0
 * @className ICollegeDao
 * @description TODO
 * @date 2025/9/10 23:12
 */
public interface ICollegeDao {
    //学院
    List<College> selectAllCollege();
}
