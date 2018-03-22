package junk;

import android.widget.Toast;

import java.util.ArrayList;

import client.RestClient;
import models.books;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

/**
 * Created by ( Jatin Bansal ) on 21-03-2018.
 */

public class junk {
    void function() {


/*
        UpchaarService libraryServiceAPI = RestClient.getClient();


        Call<ArrayList<books>> listBooksCall = libraryServiceAPI.listBooks();
        listBooksCall.enqueue(new Callback<ArrayList<books>>() {
            @Override
            public void onResponse(Call<ArrayList<books>> call, Response<ArrayList<books>> response) {
                if (response.isSuccessful()) {
                    ArrayList<books> book = response.body();
                    // Set response Books as listed layout

                } else {
                }
            }
            @Override
            public void onFailure(Call<ArrayList<books>> call, Throwable t) {

            }
        });


        books entity = new books();
        entity.setAuthor("Hello");
        entity.setName("Hello");
        entity.setPrice("2143");
        entity.setUrl("fdghj");

        Call<books> addBookCall = libraryServiceAPI.addBook(entity);
        addBookCall.enqueue(new Callback<books>() {
            @Override
            public void onResponse(Call<books> call, Response<books> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    books added = response.body();
                    if (added != null) {


                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<books> call, Throwable t) {
                t.printStackTrace();
            }
        });

        int bookId = 0;
        Call<books> checkBookInfoCall = libraryServiceAPI.getBookInfo(bookId);
        checkBookInfoCall.enqueue(new Callback<books>() {
            @Override
            public void onResponse(Call<books> call, Response<books> response) {
                if (response.isSuccessful()) {
                    books book = response.body();
                    // Set response Books as listed layout
                } else {
                }
            }

            @Override
            public void onFailure(Call<books> call, Throwable t) {

            }
        });

        Call<Void> deleteBookCall = libraryServiceAPI.deleteBook(bookId);
        deleteBookCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 204) {

                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        books book = new books();
        entity.setAuthor("Hello");
        entity.setName("Hello");
        entity.setPrice("2143");
        entity.setUrl("fdghj");

        Call<books> updateBookCall = libraryServiceAPI.updateBook(book.getName(), book);
        updateBookCall.enqueue(new Callback<books>() {
            @Override
            public void onResponse(Call<books> call, Response<books> response) {
                if (response.isSuccessful()) {
                    books updatedBook = response.body();


                } else {
                }
            }

            @Override
            public void onFailure(Call<books> call, Throwable t) {
            }
        });
        Call<Void> deleteAllCall = libraryServiceAPI.deleteAll();
        deleteAllCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {

                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
        */
    }
}
