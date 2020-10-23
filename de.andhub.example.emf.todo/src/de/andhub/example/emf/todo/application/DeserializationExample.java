package de.andhub.example.emf.todo.application;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.andhub.example.emf.todo.TodoPackage;
import de.andhub.example.emf.todo.TodoSystem;

public class DeserializationExample {
	public static void main(String[] args) {
        // Initialize the model
        TodoPackage.eINSTANCE.eClass();

        // Register the XMI resource factory for the .website extension

        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("todo", new XMIResourceFactoryImpl());

        // Obtain a new resource set
        ResourceSet resSet = new ResourceSetImpl();
        

        // Get the resource
        Resource resource = resSet.getResource(URI
                .createURI("resources/My.todo"), true);
        // Get the first model element and cast it to the right type, in my
        // example everything is hierarchical included in this first node
        TodoSystem todo = (TodoSystem) resource.getContents().get(0);
	}
}
