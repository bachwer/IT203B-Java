package Session03;

import java.util.List;
import java.util.stream.Collectors;

public class Ex6 {
    static class Post{
        private List<String> tags;

        public Post(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return tags;
        }
    }


    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post(List.of("java", "backend")),
                new Post(List.of("python", "data"))
        );

        List<String> allTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .collect(Collectors.toList());

        System.out.println(allTags);


    }
}
