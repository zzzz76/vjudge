package judge.bean;

import org.apache.struts2.json.annotations.JSON;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-07-13
 */
public class Response {
    private String name;
    private int age;
    private int id;
    @JSON(name = "name")
    public String getName() {
        return name;
    }

    public Response setName(String name) {
        this.name = name;
        return this;
    }
    @JSON(name = "age")
    public int getAge() {
        return age;
    }

    public Response setAge(int age) {
        this.age = age;
        return this;
    }
    @JSON(name = "id")
    public int getId() {
        return id;
    }

    public Response setId(int id) {
        this.id = id;
        return this;
    }
}
