<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

// Positioning
class Studie
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Studie Attributes
  private $name;
  private $beskrivelse;

  //Studie Associations
  private $institutt;
  private $prosjekts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aBeskrivelse, $aInstitutt)
  {
    $this->name = $aName;
    $this->beskrivelse = $aBeskrivelse;
    $didAddInstitutt = $this->setInstitutt($aInstitutt);
    if (!$didAddInstitutt)
    {
      throw new Exception("Unable to create study due to institutt");
    }
    $this->prosjekts = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setBeskrivelse($aBeskrivelse)
  {
    $wasSet = false;
    $this->beskrivelse = $aBeskrivelse;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getBeskrivelse()
  {
    return $this->beskrivelse;
  }

  public function getInstitutt()
  {
    return $this->institutt;
  }

  public function getProsjekt_index($index)
  {
    $aProsjekt = $this->prosjekts[$index];
    return $aProsjekt;
  }

  public function getProsjekts()
  {
    $newProsjekts = $this->prosjekts;
    return $newProsjekts;
  }

  public function numberOfProsjekts()
  {
    $number = count($this->prosjekts);
    return $number;
  }

  public function hasProsjekts()
  {
    $has = $this->numberOfProsjekts() > 0;
    return $has;
  }

  public function indexOfProsjekt($aProsjekt)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->prosjekts as $prosjekt)
    {
      if ($prosjekt->equals($aProsjekt))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setInstitutt($aInstitutt)
  {
    $wasSet = false;
    if ($aInstitutt == null)
    {
      return $wasSet;
    }
    
    $existingInstitutt = $this->institutt;
    $this->institutt = $aInstitutt;
    if ($existingInstitutt != null && $existingInstitutt != $aInstitutt)
    {
      $existingInstitutt->removeStudy($this);
    }
    $this->institutt->addStudy($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfProsjekts()
  {
    return 0;
  }

  public function addProsjektVia($aNavn, $aBeskrivelse, $aBedrift, $aEier)
  {
    return new Prosjekt($aNavn, $aBeskrivelse, $this, $aBedrift, $aEier);
  }

  public function addProsjekt($aProsjekt)
  {
    $wasAdded = false;
    if ($this->indexOfProsjekt($aProsjekt) !== -1) { return false; }
    $existingStudie = $aProsjekt->getStudie();
    $isNewStudie = $existingStudie != null && $this !== $existingStudie;
    if ($isNewStudie)
    {
      $aProsjekt->setStudie($this);
    }
    else
    {
      $this->prosjekts[] = $aProsjekt;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeProsjekt($aProsjekt)
  {
    $wasRemoved = false;
    //Unable to remove aProsjekt, as it must always have a studie
    if ($this !== $aProsjekt->getStudie())
    {
      unset($this->prosjekts[$this->indexOfProsjekt($aProsjekt)]);
      $this->prosjekts = array_values($this->prosjekts);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addProsjektAt($aProsjekt, $index)
  {  
    $wasAdded = false;
    if($this->addProsjekt($aProsjekt))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProsjekts()) { $index = $this->numberOfProsjekts() - 1; }
      array_splice($this->prosjekts, $this->indexOfProsjekt($aProsjekt), 1);
      array_splice($this->prosjekts, $index, 0, array($aProsjekt));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveProsjektAt($aProsjekt, $index)
  {
    $wasAdded = false;
    if($this->indexOfProsjekt($aProsjekt) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProsjekts()) { $index = $this->numberOfProsjekts() - 1; }
      array_splice($this->prosjekts, $this->indexOfProsjekt($aProsjekt), 1);
      array_splice($this->prosjekts, $index, 0, array($aProsjekt));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addProsjektAt($aProsjekt, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderInstitutt = $this->institutt;
    $this->institutt = null;
    $placeholderInstitutt->removeStudy($this);
    foreach ($this->prosjekts as $aProsjekt)
    {
      $aProsjekt->delete();
    }
  }

}
?>