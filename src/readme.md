studentManger/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/jxd/stuManger/
│       │       ├── dao/
│       │       │   ├── ICollegeDao.java
│       │       │   ├── CourseMapper.java
│       │       │   ├── SelectedCourseMapper.java
│       │       │   ├── StudentMapper.java
│       │       │   ├── TeacherMapper.java
│       │       │   └── UserLoginMapper.java
│       │       ├── model/
│       │       │   ├── College.java
│       │       │   ├── Course.java
│       │       │   ├── SelectedCourse.java
│       │       │   ├── Student.java
│       │       │   ├── Teacher.java
│       │       │   └── UserLogin.java
│       │       ├── service/
│       │       │   ├── AdminService.java
│       │       │   ├── AuthService.java
│       │       │   ├── StudentService.java
│       │       │   ├── TeacherService.java
│       │       │   ├── CourseService.java
│       │       │   ├── CollegeService.java
│       │       │   └── impl/
│       │       │       ├── AdminServiceImpl.java
│       │       │       ├── AuthServiceImpl.java
│       │       │       ├── StudentServiceImpl.java
│       │       │       ├── TeacherServiceImpl.java
│       │       │       ├── CourseServiceImpl.java
│       │       │       └── CollegeServiceImpl.java
│       │       ├── servlet/
│       │       │   ├── auth/
│       │       │   │   └── AuthServlet.java
│       │       │   ├── admin/
│       │       │   │   └── AdminResetPasswordServlet.java
│       │       │   ├── student/
│       │       │   │   ├── StudentServlet.java
│       │       │   │   └── StudentCourseServlet.java
│       │       │   └── teacher/
│       │       │       ├── TeacherServlet.java
│       │       │       ├── TeacherCourseServlet.java
│       │       │       └── TeacherGradeServlet.java
│       │       ├── 
│       │       └── vo/
│       │           ├── StudentWithSelectedCourse
│       ├── resources/
│       │   ├── config.xml
│       │   ├── datasource.properties
│       │   ├── log4j.properties
│       │   └── mappers/
│       │       ├── CollegeMapper.xml
│       │       ├── CourseMapper.xml
│       │       ├── SelectedCourseMapper.xml
│       │       ├── StudentMapper.xml
│       │       ├── TeacherMapper.xml
│       │       └── UserLoginMapper.xml
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── index.jsp
│           ├── login.jsp
│           ├── student_list.jsp
│           ├── student_add.jsp
│           └── student_edit.jsp
└── .gitignore