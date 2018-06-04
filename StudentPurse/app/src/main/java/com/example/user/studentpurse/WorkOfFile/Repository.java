package com.example.user.studentpurse.WorkOfFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    String FileName;

    public Repository(String fileName){
        fileName = FileName;
    }

    public List<T> getAllData() throws IOException {
        List<T> list = new ArrayList<T>();
        try {
            FileInputStream fis = new FileInputStream(FileName);
            ObjectInputStream oin = new ObjectInputStream(fis);
            try {
                list = (List<T>) oin.readObject();
                return list;
            } catch (ClassNotFoundException ex) {
                return new ArrayList<T>();
            } finally {
                oin.close();
                fis.close();
            }
        } catch (IOException e){
            throw new IOException("Возникли ошибки при загрузке данных");
        }
    }

    public void saveAllData(List<T> List) throws IOException {
        File file = new File(FileName);
        try {
            createFile();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(List);
            } catch (IOException e) {
                throw new IOException("Возникли ошибки при сохранении файла");
            } finally {
                oos.flush();
                oos.close();
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            throw new IOException("Возникли ошибки при работе с файлом");
        }
    }

    public void add(T item) throws IOException {
        try {
            ArrayList<T> allData = (ArrayList<T>) getAllData();
            allData.add(item);
            saveAllData(allData);
        } catch (IOException e) {
           throw new IOException(e.getMessage());
        }
    }

    public Boolean checkFileExists() {
        File file = new File(FileName);
        if (!file.exists())
        {
            return false;
        }
        return true;
    }

    public void createFile() throws IOException {
        if (checkFileExists())
        {
            return;
        }
        File file = new File(FileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException("Не удалось создать файл");
        }
    }
}

