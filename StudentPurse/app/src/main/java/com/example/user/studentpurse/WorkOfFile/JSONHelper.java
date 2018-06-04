package com.example.user.studentpurse.WorkOfFile;

import android.content.Context;

import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONHelper {

    private static final String FILE_NAME = "data.json";

    public static boolean exportToJSON(Context context, SmallPurseParameters dataList) throws IOException {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setPhones(dataList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            throw new IOException("При записи данных произошла ошибка");
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new IOException("При экспорте данных произошла ошибка");
                }
            }
        }
    }

    public static SmallPurseParameters importFromJSON(Context context) throws IOException {

        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return  dataItems.getParameters();
        }
        catch (IOException ex){
            throw new IOException("При импроте данных произошла ошибка");
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    throw new IOException("Ошибка потока данных");
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new IOException("Ошибка файла данных");
                }
            }
        }
    }

    private static class DataItems {
        private SmallPurseParameters parameters;

        SmallPurseParameters getParameters() {
            return parameters;
        }
        void setPhones(SmallPurseParameters parameters) {
            this.parameters = parameters;
        }
    }
}