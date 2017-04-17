package com.artivatic.artivaticsdk.utils;

import java.util.List;

/**
 * Created by root on 1/12/16.
 */

public interface ArtivaticSuggestionCallback<T> {
    public void onSuccess(List<T> response, String responseString);
    public void onError();
}