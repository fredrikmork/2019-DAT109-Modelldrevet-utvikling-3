/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/


import java.util.*;
import java.sql.Date;

// line 32 "model.ump"
// line 75 "model.ump"
public class Arrangementdeltagelse
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Arrangementdeltagelse Associations
  private Prosjekt prosjekt;
  private Arrangement arrangement;
  private List<Stemme> stemmes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Arrangementdeltagelse(Prosjekt aProsjekt, Arrangement aArrangement)
  {
    boolean didAddProsjekt = setProsjekt(aProsjekt);
    if (!didAddProsjekt)
    {
      throw new RuntimeException("Unable to create arrangementdeltagelse due to prosjekt");
    }
    boolean didAddArrangement = setArrangement(aArrangement);
    if (!didAddArrangement)
    {
      throw new RuntimeException("Unable to create arrangementdeltagelse due to arrangement");
    }
    stemmes = new ArrayList<Stemme>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Prosjekt getProsjekt()
  {
    return prosjekt;
  }
  /* Code from template association_GetOne */
  public Arrangement getArrangement()
  {
    return arrangement;
  }
  /* Code from template association_GetMany */
  public Stemme getStemme(int index)
  {
    Stemme aStemme = stemmes.get(index);
    return aStemme;
  }

  public List<Stemme> getStemmes()
  {
    List<Stemme> newStemmes = Collections.unmodifiableList(stemmes);
    return newStemmes;
  }

  public int numberOfStemmes()
  {
    int number = stemmes.size();
    return number;
  }

  public boolean hasStemmes()
  {
    boolean has = stemmes.size() > 0;
    return has;
  }

  public int indexOfStemme(Stemme aStemme)
  {
    int index = stemmes.indexOf(aStemme);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setProsjekt(Prosjekt aProsjekt)
  {
    boolean wasSet = false;
    if (aProsjekt == null)
    {
      return wasSet;
    }

    Prosjekt existingProsjekt = prosjekt;
    prosjekt = aProsjekt;
    if (existingProsjekt != null && !existingProsjekt.equals(aProsjekt))
    {
      existingProsjekt.removeArrangementdeltagelse(this);
    }
    prosjekt.addArrangementdeltagelse(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setArrangement(Arrangement aArrangement)
  {
    boolean wasSet = false;
    if (aArrangement == null)
    {
      return wasSet;
    }

    Arrangement existingArrangement = arrangement;
    arrangement = aArrangement;
    if (existingArrangement != null && !existingArrangement.equals(aArrangement))
    {
      existingArrangement.removeArrangementdeltagelse(this);
    }
    arrangement.addArrangementdeltagelse(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStemmes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Stemme addStemme(String aEpost, int aVerdi, Date aTidspunkt)
  {
    return new Stemme(aEpost, aVerdi, aTidspunkt, this);
  }

  public boolean addStemme(Stemme aStemme)
  {
    boolean wasAdded = false;
    if (stemmes.contains(aStemme)) { return false; }
    Arrangementdeltagelse existingArrangementdeltagelse = aStemme.getArrangementdeltagelse();
    boolean isNewArrangementdeltagelse = existingArrangementdeltagelse != null && !this.equals(existingArrangementdeltagelse);
    if (isNewArrangementdeltagelse)
    {
      aStemme.setArrangementdeltagelse(this);
    }
    else
    {
      stemmes.add(aStemme);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStemme(Stemme aStemme)
  {
    boolean wasRemoved = false;
    //Unable to remove aStemme, as it must always have a arrangementdeltagelse
    if (!this.equals(aStemme.getArrangementdeltagelse()))
    {
      stemmes.remove(aStemme);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStemmeAt(Stemme aStemme, int index)
  {  
    boolean wasAdded = false;
    if(addStemme(aStemme))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStemmes()) { index = numberOfStemmes() - 1; }
      stemmes.remove(aStemme);
      stemmes.add(index, aStemme);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStemmeAt(Stemme aStemme, int index)
  {
    boolean wasAdded = false;
    if(stemmes.contains(aStemme))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStemmes()) { index = numberOfStemmes() - 1; }
      stemmes.remove(aStemme);
      stemmes.add(index, aStemme);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStemmeAt(aStemme, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Prosjekt placeholderProsjekt = prosjekt;
    this.prosjekt = null;
    if(placeholderProsjekt != null)
    {
      placeholderProsjekt.removeArrangementdeltagelse(this);
    }
    Arrangement placeholderArrangement = arrangement;
    this.arrangement = null;
    if(placeholderArrangement != null)
    {
      placeholderArrangement.removeArrangementdeltagelse(this);
    }
    for(int i=stemmes.size(); i > 0; i--)
    {
      Stemme aStemme = stemmes.get(i - 1);
      aStemme.delete();
    }
  }

}