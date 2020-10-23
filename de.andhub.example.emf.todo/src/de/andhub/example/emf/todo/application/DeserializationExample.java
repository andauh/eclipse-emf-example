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
        TodoPackage.eINSTANCE.eClass();

        Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
        extensionToFactoryMap.put("todo", new XMIResourceFactoryImpl());

        ResourceSet resSet = new ResourceSetImpl();

        Resource resource = resSet.getResource(URI.createURI("resources/My.todo"), true);
        
        TodoSystem todo = (TodoSystem) resource.getContents().get(0);
	}
}
