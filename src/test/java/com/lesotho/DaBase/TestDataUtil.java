package com.lesotho.DaBase;

import com.lesotho.DaBase.domain.Author;
import com.lesotho.DaBase.domain.Book;


public final class TestDataUtil {
    public static Author createTestAuthor1() {
        return Author.builder().name("Motombo").age(30).build();
    }

    public static Author createTestAuthor2() {
        return Author.builder().name("Uvevevwe").age(12).build();
    }

    public static Author createTestAuthor3() {
        return Author.builder().name("Adimola").age(19).build();
    }


    public static Book createBook1(final Author author) {
        return Book.builder().isbn("0t843gh793g95g").title("Africa").author(author).build();
    }

    public static Book createBook2(final Author author) {
        return Book.builder().isbn("0t843gh793g95h").title("Zimbabwe").author(author).build();
    }

    public static Book createBook3(final Author author) {
        return Book.builder().isbn("0t843gh793g95j").title("Congo").author(author).build();
    }
}
