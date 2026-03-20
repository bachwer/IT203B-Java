package Session10;

public class User {
    String id;
    String name;
    int age;
    String role;
    double score;

    public User(String id, String name, int age, String role, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.role = role;
        this.score = score;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public double getScore() {
        return score;
    }


//    ----------


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        if(role.equals("USER") || role.equals("ADMIN") ){
            this.role = role;
        }else{
            System.out.println("Invalid Roles");
        }
    }

    public void setScore(double score) {
        if(score >= 0 || score <= 10){
            this.score = score;
        }else{
            System.out.println("Invalid Score");
        }
    }

    public void displayUser(){
        System.out.println("ID: "  +id +" | Name: " + name + " |Age: " +age+ " |Role: " +role  + " |Score: " + score);
    }
}
