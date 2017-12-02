package venus.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import venus.dal.model.Course;
import venus.dal.model.UserCourse;
import venus.dal.repository.UserCourseRepository;
import venus.logic.dto.UserCourseDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCourseServiceImpl implements UserCourseService {
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    @Transactional
    public void save(UserCourse userCourse) {
        //@TODO: Remove this shit
        List<UserCourse> userCourses = userCourse
            .getStudent()
            .getUserCoursesBySubjectId(userCourse
                    .getCourse()
                    .getSubject()
                    .getId());

        for(int i = 0; i < userCourses.size(); ++i) {
            this.delete(userCourses.get(i));
        }
        userCourseRepository.save(userCourse);
    }

    @Override
    @Transactional
    public void delete(UserCourse userCourse) {
        userCourse.getStudent().getUserCourses().remove(userCourse);
        userCourse.getCourse().getStudentCourses().remove(userCourse);
        userCourseRepository.delete(userCourse);
    }

    public List<UserCourseDTO> convertToDTOs(Course course) {
        return course
            .getStudentCourses()
            .stream()
            .map(n -> UserCourseDTO.create(n))
            .collect(Collectors.toList());
    }
}
