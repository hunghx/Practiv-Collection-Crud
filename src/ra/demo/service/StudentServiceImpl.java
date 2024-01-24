package ra.demo.service;

import ra.demo.model.Student;
import ra.demo.util.InputMethods;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private static List<Student> studentList ;
    static {
        try {
            studentList = new ArrayList<>(Arrays.asList(
                    new Student(1,"Hồ Xuân Hùng", InputMethods.sdf.parse("1/1/2000"),true),
                    new Student(2,"Nguyễn Duy Quang", InputMethods.sdf.parse("1/1/2007"),true),
                    new Student(3,"Nguyễn Viết Lâm", InputMethods.sdf.parse("1/1/2009"),false)
            ));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Student findById(Integer id) {
        for (Student s : studentList) {
            if (s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void save(Student student) {
        if (findById(student.getStudentId())==null){
        // theem moi
            student.setStudentId(getNewId());
            studentList.add(student);
        }else {
            // cap nhat
            studentList.set(studentList.indexOf(findById(student.getStudentId())),student);
        }
    }

    @Override
    public void deleteById(Integer id) {
            studentList.remove(findById(id));
    }

    @Override
    public List<Student> findByName(String name) {
        return studentList.stream()
                .filter(
                        (stu)->stu.getStudentName()
                                .toLowerCase()
                                .contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public int getNewId() {
        int maxId = 0;
        for (Student s: studentList){
            if (maxId < s.getStudentId()){
                maxId = s.getStudentId();
            }
        }
        return maxId+1;
    }
}
