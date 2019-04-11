/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;
import java.sql.Date;

// line 44 "model.ump"
// line 88 "model.ump"
public class Stemmemetode
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stemmemetode Attributes
  private String navn;
  private int parameter;

  //Stemmemetode Associations
  private List<Arrangement> arrangements;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Stemmemetode(String aNavn, int aParameter)
  {
    navn = aNavn;
    parameter = aParameter;
    arrangements = new ArrayList<Arrangement>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNavn(String aNavn)
  {
    boolean wasSet = false;
    navn = aNavn;
    wasSet = true;
    return wasSet;
  }

  public boolean setParameter(int aParameter)
  {
    boolean wasSet = false;
    parameter = aParameter;
    wasSet = true;
    return wasSet;
  }

  public String getNavn()
  {
    return navn;
  }

  public int getParameter()
  {
    return parameter;
  }
  /* Code from template association_GetMany */
  public Arrangement getArrangement(int index)
  {
    Arrangement aArrangement = arrangements.get(index);
    return aArrangement;
  }

  public List<Arrangement> getArrangements()
  {
    List<Arrangement> newArrangements = Collections.unmodifiableList(arrangements);
    return newArrangements;
  }

  public int numberOfArrangements()
  {
    int number = arrangements.size();
    return number;
  }

  public boolean hasArrangements()
  {
    boolean has = arrangements.size() > 0;
    return has;
  }

  public int indexOfArrangement(Arrangement aArrangement)
  {
    int index = arrangements.indexOf(aArrangement);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArrangements()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Arrangement addArrangement(String aNavn, String aBeskrivelse, Date aUtgaar)
  {
    return new Arrangement(aNavn, aBeskrivelse, aUtgaar, this);
  }

  public boolean addArrangement(Arrangement aArrangement)
  {
    boolean wasAdded = false;
    if (arrangements.contains(aArrangement)) { return false; }
    Stemmemetode existingStemmemetode = aArrangement.getStemmemetode();
    boolean isNewStemmemetode = existingStemmemetode != null && !this.equals(existingStemmemetode);
    if (isNewStemmemetode)
    {
      aArrangement.setStemmemetode(this);
    }
    else
    {
      arrangements.add(aArrangement);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArrangement(Arrangement aArrangement)
  {
    boolean wasRemoved = false;
    //Unable to remove aArrangement, as it must always have a stemmemetode
    if (!this.equals(aArrangement.getStemmemetode()))
    {
      arrangements.remove(aArrangement);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArrangementAt(Arrangement aArrangement, int index)
  {  
    boolean wasAdded = false;
    if(addArrangement(aArrangement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrangements()) { index = numberOfArrangements() - 1; }
      arrangements.remove(aArrangement);
      arrangements.add(index, aArrangement);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArrangementAt(Arrangement aArrangement, int index)
  {
    boolean wasAdded = false;
    if(arrangements.contains(aArrangement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrangements()) { index = numberOfArrangements() - 1; }
      arrangements.remove(aArrangement);
      arrangements.add(index, aArrangement);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArrangementAt(aArrangement, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=arrangements.size(); i > 0; i--)
    {
      Arrangement aArrangement = arrangements.get(i - 1);
      aArrangement.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "navn" + ":" + getNavn()+ "," +
            "parameter" + ":" + getParameter()+ "]";
  }
}