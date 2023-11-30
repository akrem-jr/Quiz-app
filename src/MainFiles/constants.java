package MainFiles;

import java.awt.*;

public interface constants {
    char NONE = 'N';
    char USER = 'U';
    char ADMIN = 'A';
    char SCHOOL = 'S';
    int size[] = {500, 500};
    String title = "Quiz App";
    String USER_FUN[] = {
            "Take Quiz", "See Your Mark", "Log Out"};
    String NON_USER_FUN[] = {"Log In", "Register"};
    String SCHOOL_FUN[] = {"Add Question", "Log Out"};
    String ADMIN_FUN[] = {"View Request", "Log Out"};
    String CONTROLLER_TYPE[] = {"Log In", "Register",
            "View Request", "Take Quiz",
            "See Your Mark", "Top Anwsers",  "Add Question","Log Out"};
    String CONTROLLER_DESC[] = {"Get Access to do stuff", "Get Account",
             "0 Requests", "0 Available",
            "Answered 0 Questions", "See Who answers Your questions","Add Question","Exit"};
    String ICON_PATH[] = { "\\Icons\\user.png",
                    "\\Icons\\register.png",
                    "\\Icons\\list.png",
                    "\\Icons\\quiz.png",
                    "\\Icons\\grade.png",
            "\\Icons\\rank.png",
            "\\Icons\\plus.png",
            "\\Icons\\logout.png"
    };
    String NAV_ICONS[] = {"\\Icons\\school.png",
            "\\Icons\\register_for_nav.png",
            "\\Icons\\admin.png"
    };
    String NAV_ITEMS[] = {"Log In As School ", "Register Your School ", "Admin "};
    String USER_FILE = ".\\src\\MainFiles\\db_files\\users_info.csv";
    String SCHOOL_FILE = ".\\src\\MainFiles\\db_files\\school_info.csv";
    String SCHOOL_FILE_REQ = ".\\src\\MainFiles\\db_files\\school_request.csv";
    String ADMIN_FILE = ".\\src\\MainFiles\\db_files\\admin_info.csv";
    String QUIZ_FILE = ".\\src\\MainFiles\\db_files\\quiz.csv";
    String NONE_PAGE = "NO_PAGE";
    String LOOG_IN = "LOG_IN";
    String ADMIN_LOG_IN = "ADM_LOG_IN";
    String USER_REGISTER = "USER_REG";
    String ADMIN_REQ = "ADM_REQ";
    String READY_FOR_QUIZ = "READY";
    String QUIZ = "QUIZ";
    String SCHOOL_LOG_IN = "SCH_LOG_IN";
    String ADD_QUESTION = "ADD_D";
    Color backgroundColor = new Color(238, 238, 238, 255);
    Color selectedBtn = new Color(145, 153, 196, 255);
    Color clickedBtn = new Color(55, 147, 208);

}
