package gob.osinergmin.fise.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class TrimUtil {
	
	static final Log logger=LogFactoryUtil.getLog(TrimUtil.class);
	
	public static Object trimReflective(Object object) throws Exception {
        if (object == null)
            return null;

        Class<? extends Object> c = object.getClass();
        try {
            // Introspector usage to pick the getters conveniently thereby
            // excluding the Object getters
        	logger.info(1);
        	for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(c, Object.class).getPropertyDescriptors()) {
        		logger.info(2);
        		Method method = propertyDescriptor.getReadMethod();
        		logger.info(3);
        		String name = method.getName();
                logger.info(4);
                // If the current level of Property is of type String
                if (method.getReturnType().equals(String.class)) {
                	logger.info(5);
                    String property = (String) method.invoke(object);
                    logger.info(6);
                    if (property != null) {
                    	logger.info(7);
                        Method setter = c.getMethod("set" + name.substring(3), new Class<?>[] { String.class });
                        logger.info(8);
                        if (setter != null)
                        	logger.info(9);
                            // Setter to trim and set the trimmed String value
                            setter.invoke(object, property.trim());
                            logger.info(10);
                    }
                }

                // If an Object Array of Properties - added additional check to
                // avoid getBytes returning a byte[] and process
                logger.info(11);
                if (method.getReturnType().isArray()
                        && !method.getReturnType().isPrimitive()
                        && !method.getReturnType().equals(String[].class)
                        && !method.getReturnType().equals(byte[].class)) {
                	logger.info(12);
                    System.out.println(method.getReturnType());
                    logger.info(method.getReturnType());
                    // Type check for primitive arrays (would fail typecasting
                    // in case of int[], char[] etc)
                    if (method.invoke(object) instanceof Object[]) {
                    	logger.info(13);
                        Object[] objectArray = (Object[]) method.invoke(object);
                        logger.info(14);
                        if (objectArray != null) {
                        	logger.info(15);
                            for (Object obj : (Object[]) objectArray) {
                            	logger.info(16);
                                // Recursively revisit with the current property
                                trimReflective(obj);
                            }
                        }
                    }
                }
                // If a String array
                if (method.getReturnType().equals(String[].class)) {
                    String[] propertyArray = (String[]) method.invoke(object);
                    if (propertyArray != null) {
                        Method setter = c.getMethod("set" + name.substring(3), new Class<?>[] { String[].class });
                        if (setter != null) {
                            String[] modifiedArray = new String[propertyArray.length];
                            for (int i = 0; i < propertyArray.length; i++)
                                if (propertyArray[i] != null)
                                    modifiedArray[i] = propertyArray[i].trim();

                            // Explicit wrapping
                            setter.invoke(object, new Object[] { modifiedArray });
                        }
                    }
                }
                // Collections start
                if (Collection.class.isAssignableFrom(method.getReturnType())) {
                    Collection collectionProperty = (Collection) method.invoke(object);
                    if (collectionProperty != null) {
                        for (int index = 0; index < collectionProperty.size(); index++) {
                            if (collectionProperty.toArray()[index] instanceof String) {
                                String element = (String) collectionProperty.toArray()[index];

                                if (element != null) {
                                    // Check if List was created with
                                    // Arrays.asList (non-resizable Array)
                                    if (collectionProperty instanceof List) {
                                        ((List) collectionProperty).set(index, element.trim());
                                    } else {
                                        collectionProperty.remove(element);
                                        collectionProperty.add(element.trim());
                                    }
                                }
                            } else {
                                // Recursively revisit with the current property
                                trimReflective(collectionProperty.toArray()[index]);
                            }
                        }
                    }
                }
                // Separate placement for Map with special conditions to process
                // keys and values
                if (method.getReturnType().equals(Map.class)) {
                    Map mapProperty = (Map) method.invoke(object);
                    if (mapProperty != null) {
                        // Keys
                        for (int index = 0; index < mapProperty.keySet().size(); index++) {
                            if (mapProperty.keySet().toArray()[index] instanceof String) {
                                String element = (String) mapProperty.keySet().toArray()[index];
                                if (element != null) {
                                    mapProperty.put(element.trim(), mapProperty.get(element));
                                    mapProperty.remove(element);
                                }
                            } else {
                                // Recursively revisit with the current property
                                trimReflective(mapProperty.get(index));
                            }

                        }
                        // Values
                        for (Map.Entry entry : (Set<Map.Entry>) mapProperty.entrySet()) {

                            if (entry.getValue() instanceof String) {
                                String element = (String) entry.getValue();
                                if (element != null) {
                                    entry.setValue(element.trim());
                                }
                            } else {
                                // Recursively revisit with the current property
                                trimReflective(entry.getValue());
                            }
                        }
                    }
                } else {// Catch a custom data type as property and send through
                        // recursion
                    Object property = (Object) method.invoke(object);
                    if (property != null) {
                        trimReflective(property);
                    }
                }
            }

        } catch (Exception e) {
        	logger.error("Strings cannot be trimmed because: "+ e.getMessage());
            throw new Exception("Strings cannot be trimmed because: ", e);
        }

        return object;

    }
	//////
	
	public static void trimAllStrings(Object beanObject) throws Exception {
		  Exception noSuchMethodException = null;
		  boolean throwNoSuchMethodException = false;
		  if (beanObject != null) {

		    Method[] methods = null;

		    try {
		    methods = beanObject.getClass().getMethods();
		   } catch (SecurityException e) {
		    throw new Exception(e);
		   }

		    if (methods != null) {

		     for (Method method : methods) {

		      String methodName = method.getName();

		      if (!methodName.equals("getClass")) {

		       String returnType = method.getReturnType().toString();
		      String commonMethodName = null;

		       if (methodName.startsWith("get")
		        && "class java.lang.String".equals(returnType)) {

		        commonMethodName = methodName.replaceFirst("get",
		         "");
		       String returnedValue = null;

		        try {
		        returnedValue = (String) method
		          .invoke(beanObject);
		       } catch (IllegalArgumentException e) {
		        e.printStackTrace();
		        throw e;
		       } catch (IllegalAccessException e) {
		        e.printStackTrace();
		        throw e;
		       } catch (InvocationTargetException e) {
		        e.printStackTrace();
		        throw e;
		       }

		        if (returnedValue != null) {

		         StringBuilder setterMethodName = new StringBuilder();
		        setterMethodName.append("set");
		        setterMethodName.append(commonMethodName);
		        Method setterMethod = null;

		         try {
		         setterMethod = beanObject
		           .getClass()
		           .getMethod(
		             setterMethodName.toString(),
		             String.class);
		         if (setterMethod != null) {
		          setterMethod.invoke(beanObject,
		            (returnedValue.trim()));
		         }
		        } catch (SecurityException e) {
		         e.printStackTrace();
		         throw e;
		        } catch (NoSuchMethodException e) {
		         e.printStackTrace();
		         if (!throwNoSuchMethodException) {
		          noSuchMethodException = e;
		         }
		         throwNoSuchMethodException = true;
		        } catch (IllegalArgumentException e) {
		         e.printStackTrace();
		         throw e;
		        } catch (IllegalAccessException e) {
		         e.printStackTrace();
		         throw e;
		        } catch (InvocationTargetException e) {
		         e.printStackTrace();
		         throw e;
		        }
		       }
		      }
		     }
		    }
		   }
		  }

		   if (throwNoSuchMethodException && noSuchMethodException != null) {
		   throw noSuchMethodException;
		  }
		 }

		  /**
		  * converts an Object to String
		  * 
		  * @param object1
		  * @return String
		  * @author visruth
		  */
		 public String toString(Object object) {
		  if (object != null) {
		   return object.toString();
		  } else {
		   return null;
		  }
		 }

	
}
