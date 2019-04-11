<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Arrangementdeltagelse
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Arrangementdeltagelse Associations
  private $prosjekt;
  private $arrangement;
  private $stemmes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aProsjekt, $aArrangement)
  {
    $didAddProsjekt = $this->setProsjekt($aProsjekt);
    if (!$didAddProsjekt)
    {
      throw new Exception("Unable to create arrangementdeltagelse due to prosjekt");
    }
    $didAddArrangement = $this->setArrangement($aArrangement);
    if (!$didAddArrangement)
    {
      throw new Exception("Unable to create arrangementdeltagelse due to arrangement");
    }
    $this->stemmes = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getProsjekt()
  {
    return $this->prosjekt;
  }

  public function getArrangement()
  {
    return $this->arrangement;
  }

  public function getStemme_index($index)
  {
    $aStemme = $this->stemmes[$index];
    return $aStemme;
  }

  public function getStemmes()
  {
    $newStemmes = $this->stemmes;
    return $newStemmes;
  }

  public function numberOfStemmes()
  {
    $number = count($this->stemmes);
    return $number;
  }

  public function hasStemmes()
  {
    $has = $this->numberOfStemmes() > 0;
    return $has;
  }

  public function indexOfStemme($aStemme)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->stemmes as $stemme)
    {
      if ($stemme->equals($aStemme))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setProsjekt($aProsjekt)
  {
    $wasSet = false;
    if ($aProsjekt == null)
    {
      return $wasSet;
    }
    
    $existingProsjekt = $this->prosjekt;
    $this->prosjekt = $aProsjekt;
    if ($existingProsjekt != null && $existingProsjekt != $aProsjekt)
    {
      $existingProsjekt->removeArrangementdeltagelse($this);
    }
    $this->prosjekt->addArrangementdeltagelse($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setArrangement($aArrangement)
  {
    $wasSet = false;
    if ($aArrangement == null)
    {
      return $wasSet;
    }
    
    $existingArrangement = $this->arrangement;
    $this->arrangement = $aArrangement;
    if ($existingArrangement != null && $existingArrangement != $aArrangement)
    {
      $existingArrangement->removeArrangementdeltagelse($this);
    }
    $this->arrangement->addArrangementdeltagelse($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfStemmes()
  {
    return 0;
  }

  public function addStemmeVia($aEpost, $aVerdi, $aTidspunkt)
  {
    return new Stemme($aEpost, $aVerdi, $aTidspunkt, $this);
  }

  public function addStemme($aStemme)
  {
    $wasAdded = false;
    if ($this->indexOfStemme($aStemme) !== -1) { return false; }
    $existingArrangementdeltagelse = $aStemme->getArrangementdeltagelse();
    $isNewArrangementdeltagelse = $existingArrangementdeltagelse != null && $this !== $existingArrangementdeltagelse;
    if ($isNewArrangementdeltagelse)
    {
      $aStemme->setArrangementdeltagelse($this);
    }
    else
    {
      $this->stemmes[] = $aStemme;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStemme($aStemme)
  {
    $wasRemoved = false;
    //Unable to remove aStemme, as it must always have a arrangementdeltagelse
    if ($this !== $aStemme->getArrangementdeltagelse())
    {
      unset($this->stemmes[$this->indexOfStemme($aStemme)]);
      $this->stemmes = array_values($this->stemmes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStemmeAt($aStemme, $index)
  {  
    $wasAdded = false;
    if($this->addStemme($aStemme))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStemmes()) { $index = $this->numberOfStemmes() - 1; }
      array_splice($this->stemmes, $this->indexOfStemme($aStemme), 1);
      array_splice($this->stemmes, $index, 0, array($aStemme));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStemmeAt($aStemme, $index)
  {
    $wasAdded = false;
    if($this->indexOfStemme($aStemme) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStemmes()) { $index = $this->numberOfStemmes() - 1; }
      array_splice($this->stemmes, $this->indexOfStemme($aStemme), 1);
      array_splice($this->stemmes, $index, 0, array($aStemme));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStemmeAt($aStemme, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderProsjekt = $this->prosjekt;
    $this->prosjekt = null;
    $placeholderProsjekt->removeArrangementdeltagelse($this);
    $placeholderArrangement = $this->arrangement;
    $this->arrangement = null;
    $placeholderArrangement->removeArrangementdeltagelse($this);
    foreach ($this->stemmes as $aStemme)
    {
      $aStemme->delete();
    }
  }

}
?>