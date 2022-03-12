package com.asgarov;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Map;

public class Runner {


    public static void main(String[] args) throws JAXBException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Bean.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Bean bean = (Bean) jaxbUnmarshaller.unmarshal(new File("src/main/resources/config.xml"));
        System.out.println();

        MyCustomClassLoader myCustomClassLoader = new MyCustomClassLoader();
        myCustomClassLoader.setBean(bean);

        Person person = (Person) myCustomClassLoader.getBean("Person");

//        writeBeanToXml();
    }

    private static void writeBeanToXml() throws JAXBException, FileNotFoundException {
        JAXBContext contextObj = JAXBContext.newInstance(Bean.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.marshal(new Bean(1, "com.asgarov.Person", Map.of("String", "Javid")), new FileOutputStream("beans.xml"));
    }
}
