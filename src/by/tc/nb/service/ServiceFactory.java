package by.tc.nb.service;

import by.tc.nb.service.impl.NoteBookServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private NoteBookService nbService =  new NoteBookServiceImpl();



    public static ServiceFactory getInstance(){
        return instance;
    }


    public NoteBookService getNoteBookService(){
        return nbService;
    }

}
