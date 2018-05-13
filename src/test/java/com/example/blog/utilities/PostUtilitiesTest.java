package com.example.blog.utilities;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostUtilitiesTest {

    @Test
    public void testGetTagsFromPost(){
        List<String> descriptions = new ArrayList<>();
        descriptions.add("#Lata sada #patataj,. #test lucky");
        descriptions.add("Kształt #kasztahn #kasztahn lucky, a######## # # dasdasasd");
        descriptions.add(", nose ##Fgh Patataj #sad() #a # #* #@User ##@");
        List<List<String>> expectedTagsFromDescriptions = new ArrayList<>();
        expectedTagsFromDescriptions.add(new ArrayList<>(Arrays.asList("lata", "patataj", "test")));
        expectedTagsFromDescriptions.add(new ArrayList<>(Arrays.asList("kasztahn")));
        expectedTagsFromDescriptions.add(new ArrayList<>(Arrays.asList("fgh", "sad", "a", "user")));
        List<List<String>> formattedTags = formatTagList(descriptions);
        assertThat(formattedTags, is(expectedTagsFromDescriptions));
    }


    private List<List<String>> formatTagList(List<String> descriptions){
        List<List<String>> formattedTags = new ArrayList<>();
        descriptions.forEach(description -> formattedTags.add(PostUtilities.getTags(description)));
        return formattedTags;
    }
}
