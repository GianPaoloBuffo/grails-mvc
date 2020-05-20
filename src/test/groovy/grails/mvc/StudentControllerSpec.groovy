package grails.mvc

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class StudentControllerSpec extends Specification implements ControllerUnitTest<StudentController> {

    void "test the index action returns the correct model"() {
        given:
        controller.studentService = Mock(StudentService) {
            list() >> [new Student(firstName: 'John', lastName: 'Doe')]
        }

        when: "the index action is executed"
        controller.index()

        then: "the model is correct"
        model.studentList.size() == 1
        model.studentList[0].firstName == 'John'
        model.studentList[0].lastName == 'Doe'
    }

    void "test the delete action with an instance"() {
        given:
        controller.studentService = Mock(StudentService) {
            1 * delete(2)
        }

        when: "the domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then: "the user is redirected to index"
        response.redirectedUrl == '/student/index'
    }
}
