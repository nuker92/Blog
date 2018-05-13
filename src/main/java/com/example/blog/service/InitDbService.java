package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class InitDbService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    private IPostService postService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public InitDbService(RoleRepository roleRepository, UserRepository userRepository, CommentRepository commentRepository, IPostService postService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @PostConstruct
    public void init(){
        if(roleRepository.findByRole("ROLE_ADMIN") == null){
            Role roleUser = new Role();
            roleUser.setRole("ROLE_USER");
            roleRepository.save(roleUser);

            Role roleAdmin = new Role();
            roleAdmin.setRole("ROLE_ADMIN");
            roleRepository.save(roleAdmin);

            User userAdmin = new User("admin@blog.com", bCryptPasswordEncoder.encode("123"), "John", "Smith", "Admin", 1);
            userAdmin.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
            userRepository.save(userAdmin);

            User normalUser = new User("normalUser@blog.com", bCryptPasswordEncoder.encode("123"), "Donald", "Clinton", "President", 1);
            normalUser.setRoles(new HashSet<>(Arrays.asList(roleUser)));
            userRepository.save(normalUser);

            Post post1 = new Post("#test Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eu felis eu erat venenatis dignissim. Phasellus diam erat, facilisis sed turpis dictum, volutpat convallis sapien. Aliquam erat volutpat. Nunc bibendum, diam at efficitur lobortis, lorem metus posuere dui, et rhoncus ante nibh eget eros. Sed lacus ipsum, dictum ut posuere a, consectetur ullamcorper nisi. Mauris sed sapien erat. Sed eget accumsan tortor. Maecenas faucibus risus ipsum, ut sodales augue accumsan quis. Aenean eget enim nec ipsum consequat suscipit. Maecenas varius purus et elit placerat scelerisque. Aenean non risus vel risus elementum sodales et vel ipsum. " , userAdmin, LocalDateTime.of(2015, 1, 1, 10, 0));
            Post post2 = new Post("#test Vestibulum non libero neque. Vestibulum auctor mattis dui vitae maximus. Fusce vel pulvinar nisl, sit amet ullamcorper libero. Mauris vitae enim volutpat, imperdiet tellus vitae, consectetur metus. Nam urna justo, dapibus eu ex sit amet, sodales vulputate quam. Nunc id nunc justo. Suspendisse finibus consequat auctor. Pellentesque metus odio, convallis in dapibus eu, pretium molestie ipsum. Nulla ac commodo dui. Vivamus vel pellentesque lectus. Ut quis posuere dui. Nunc vitae nulla in est venenatis viverra sed luctus velit. Nunc ut ligula vitae lacus convallis elementum nec vitae arcu. Quisque dignissim, tortor vitae suscipit fermentum, enim sem pretium est, eget interdum risus lorem nec metus. ", userAdmin, LocalDateTime.of(2015, 2, 1, 10, 0));
            Post post3 = new Post("#kala ictum consectetur, velit purus semper lacus, in egestas diam purus a ipsum. Maecenas in velit pulvinar, feugiat leo sed, tincidunt nulla. Vestibulum aliquet vitae odio et tincidunt. Fusce sed tortor nisl. Nulla facilisi. Sed vitae luctus turpis. Vivamus sed odio quam. Cras eget aliquet quam, eu maximus risus. ", userAdmin, LocalDateTime.of(2015, 2, 12, 10, 0));
            Post post4 = new Post("#home Vivamus in consequat est, in sagittis diam. Curabitur blandit augue non mollis aliquam. Maecenas non pretium augue. Etiam in sodales sem. Nulla laoreet, dui sed ", normalUser, LocalDateTime.of(2016, 1, 1, 10, 0));
            Post post5 = new Post("#nose velit mattis euismod. Cras mauris sem, cursus a accumsan eget, tincidunt quis tortor. Ut vel velit sit amet nunc imperdiet tincidunt. Aliquam vestibulum augue vitae tellus efficitur, quis elementum eros accumsan. Aenean pharetra felis nec lectus efficitur dapibus ut mattis odio. Fusce vestibulu", normalUser, LocalDateTime.of(2016, 1, 2, 10, 0));
            Post post6 = new Post("#nose Short posts", normalUser, LocalDateTime.of(2016, 2, 2, 10, 0));
            Post post7 = new Post("#eggs m in sodales sem. Nulla laoreet, dui sed dictum consectetur, velit purus semper lacus, in egestas diam purus a ipsum. Maecenas in velit pulvinar, feugiat leo sed, tincidunt nulla. Vestibulum aliquet vitae odio et tincidunt. Fusce sed tortor nisl. Nulla facilisi. Sed vitae luctus turpis. Viva", normalUser, LocalDateTime.of(2016, 3, 2, 10, 0));
            Post post8 = new Post("#eggs Added by notmal User", normalUser, LocalDateTime.of(2016, 4, 2, 10, 0));
            Post post9 = new Post("#eggs sdasgdsfgdskfjglskdfgj kldsjfglskdjgflksgdfj", normalUser, LocalDateTime.of(2016, 5, 2, 10, 0));
            Post post10 = new Post("#home  Suspendisse eu felis eu erat venenatis dignissim. Phasellus diam erat, facilisis sed turpis dictum, volutpat convallis sapien. Aliquam erat volutpat. Nunc bibendum, diam at efficitur lobortis, lorem metus posuere dui, et rhoncus ante nibh eget eros. Sed lacus ipsum, dictum ut p", normalUser, LocalDateTime.of(2016, 6, 2, 10, 0));
            Post post11 = new Post("#nose uris sem, cursus a accumsan eget, tincidunt quis tortor. Ut vel velit sit amet nunc imperdiet tincidunt. Aliquam vestibulum augue vitae tellus efficitur, quis elementum eros accumsan. Aenean pharetra felis nec lectus efficitur dapibus ut mattis odio. Fusce vestibulum sapien nulla, a facilisis nibh finibus feugiat. Nullam viverra interdum felis nec porttitor. Curabitur tincidunt ipsum risus, ut ultricies neque rhoncus nec. Fusce lacus leo, biben", normalUser, LocalDateTime.of(2016, 7, 2, 10, 0));
            Post post12 = new Post("Post without tags", normalUser, LocalDateTime.of(2016, 8, 2, 10, 0));
            List<Post> posts = Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10, post11, post12);
            posts.forEach(post -> postService.createPost(post, post.getUser().getEmail()));

            Comment comment1 = new Comment("get, sollicitudin nec magna. Donec at purus quis eros tempus varius. Mauris arcu elit, mollis at turpis porta, lobortis viverra massa. Ut rutrum purus iaculis sapien mattis egestas. Aliquam sed eros elementum, euismod turpis at, porta nulla. Ut at vulputate ante. Vivamus varius libero eget urna ultrices lobortis. Duis sollicitudin, mi a semper mattis, risus lacus posuere sapien, eget consequat lorem nunc nec velit. Don", userAdmin, post1, LocalDateTime.now());
            Comment comment2 = new Comment("at, imperdiet tellus vitae, consectetur metus. Nam urna justo, dapibus eu ex sit amet, sodales vulputate quam. Nunc id nunc justo. Suspendisse finibus consequat auctor. Pellentesque metus odio, convallis in dapibus eu, pretium molestie ipsum. Nulla ac commodo dui. Viva", normalUser, post1, LocalDateTime.now());
            Comment comment3 = new Comment("um purus iaculis sapien mattis egestas. Aliquam sed eros elementum, euismod turpis at, porta nulla. Ut at vulputate ante. Vivamus varius libero eget urna ultrices lobortis. Duis sollicitudin, mi a semper mattis, risus lacus posuere sapien, eget consequat lorem nunc nec velit. D", userAdmin, post12, LocalDateTime.now());
            Comment comment4 = new Comment("us tristique. Proin varius, nulla egestas maximus tempus, metus purus consequat felis, at facilisis neque neque sit amet arcu. Vivamus in consequat est, in sagittis diam. Curabitur blandit augue non mollis aliquam. Maecenas non pretium augue. Etiam in sodales sem. Nulla laoreet, dui sed dictum consectetur, velit purus semper lacus, in egestas diam purus a ipsum. Maecenas in velit pulvinar, feugiat leo sed, tincidunt nulla. Vestibulum aliquet vitae odio et tincidunt. Fusce sed tortor nisl. Nulla facilisi. Sed vitae luctus turpis. Vivamus sed odio quam. Cras eget", normalUser, post12, LocalDateTime.now());
            List<Comment> comments = Arrays.asList(comment1, comment2, comment3, comment4);
            comments.forEach(comment -> commentRepository.save(comment));



        }
    }



}
