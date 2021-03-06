/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 38 "../../../../../SMSS.ump"
public class SenderObject extends Object
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, SenderObject> senderobjectsByName = new HashMap<String, SenderObject>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SenderObject Attributes
  private String name;

  //SenderObject Associations
  private List<Message> messages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SenderObject(ClassType aClassType, String aName)
  {
    super(aClassType);
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    messages = new ArrayList<Message>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      senderobjectsByName.remove(anOldName);
    }
    senderobjectsByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static SenderObject getWithName(String aName)
  {
    return senderobjectsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetMany */
  public Message getMessage(int index)
  {
    Message aMessage = messages.get(index);
    return aMessage;
  }

  public List<Message> getMessages()
  {
    List<Message> newMessages = Collections.unmodifiableList(messages);
    return newMessages;
  }

  public int numberOfMessages()
  {
    int number = messages.size();
    return number;
  }

  public boolean hasMessages()
  {
    boolean has = messages.size() > 0;
    return has;
  }

  public int indexOfMessage(Message aMessage)
  {
    int index = messages.indexOf(aMessage);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addMessage(String aName, ReceiverObject aReceiverObject)
  {
    return new Message(aName, this, aReceiverObject);
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    SenderObject existingSenderObject = aMessage.getSenderObject();
    boolean isNewSenderObject = existingSenderObject != null && !this.equals(existingSenderObject);
    if (isNewSenderObject)
    {
      aMessage.setSenderObject(this);
    }
    else
    {
      messages.add(aMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessage(Message aMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aMessage, as it must always have a senderObject
    if (!this.equals(aMessage.getSenderObject()))
    {
      messages.remove(aMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMessageAt(Message aMessage, int index)
  {  
    boolean wasAdded = false;
    if(addMessage(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessageAt(Message aMessage, int index)
  {
    boolean wasAdded = false;
    if(messages.contains(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessageAt(aMessage, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    senderobjectsByName.remove(getName());
    for(int i=messages.size(); i > 0; i--)
    {
      Message aMessage = messages.get(i - 1);
      aMessage.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}