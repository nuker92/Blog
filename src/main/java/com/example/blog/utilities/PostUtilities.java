package com.example.blog.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostUtilities {

    public static List<String> getTags(String description) {
        String words[] = description.split(" ");
        List<String> tags = Arrays.stream(words).filter(w -> w.startsWith("#")).collect(Collectors.toList());
        List<String> formattedTags = tags.stream()
                .map(t -> t.replaceAll("[,.\\-\\_+=\\{\\[\\}\\]\\|\\\\;:'\"<>\\/?!@$%^&*()]", ""))
                .map(t -> t.replaceAll("[#]{2,}", "#"))
                .filter(t -> t.length() > 1)
                .map(t -> t.toLowerCase())
                .map(t -> t.replaceAll("#", ""))
                .distinct()
                .collect(Collectors.toList());
        return formattedTags;
    }

}
