package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.UserCourse;
import venus.dal.repository.UserCourseRepository;

import javax.transaction.Transactional;

@Service
public class UserCourseServiceImpl implements UserCourseService {
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    @Transactional
    public void save(UserCourse userCourse) {
        userCourse.getStudent()
                .getUserCourses()
                .forEach(n -> userCourseRepository.delete(n));

        userCourseRepository.save(userCourse);
    }

    @Override
    @Transactional
    public void delete(UserCourse userCourse) {
        userCourse.getStudent().getUserCourses().remove(userCourse);
        userCourse.getCourse().getStudentCourses().remove(userCourse);
        userCourseRepository.delete(userCourse);
    }
}
