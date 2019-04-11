<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Arrangement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Arrangement Attributes
  private $navn;
  private $beskrivelse;
  private $utgaar;

  //Arrangement Associations
  private $stemmemetode;
  private $arrangementdeltagelses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aNavn, $aBeskrivelse, $aUtgaar, $aStemmemetode)
  {
    $this->navn = $aNavn;
    $this->beskrivelse = $aBeskrivelse;
    $this->utgaar = $aUtgaar;
    $didAddStemmemetode = $this->setStemmemetode($aStemmemetode);
    if (!$didAddStemmemetode)
    {
      throw new Exception("Unable to create arrangement due to stemmemetode");
    }
    $this->arrangementdeltagelses = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setNavn($aNavn)
  {
    $wasSet = false;
    $this->navn = $aNavn;
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

  public function setUtgaar($aUtgaar)
  {
    $wasSet = false;
    $this->utgaar = $aUtgaar;
    $wasSet = true;
    return $wasSet;
  }

  public function getNavn()
  {
    return $this->navn;
  }

  public function getBeskrivelse()
  {
    return $this->beskrivelse;
  }

  public function getUtgaar()
  {
    return $this->utgaar;
  }

  public function getStemmemetode()
  {
    return $this->stemmemetode;
  }

  public function getArrangementdeltagelse_index($index)
  {
    $aArrangementdeltagelse = $this->arrangementdeltagelses[$index];
    return $aArrangementdeltagelse;
  }

  public function getArrangementdeltagelses()
  {
    $newArrangementdeltagelses = $this->arrangementdeltagelses;
    return $newArrangementdeltagelses;
  }

  public function numberOfArrangementdeltagelses()
  {
    $number = count($this->arrangementdeltagelses);
    return $number;
  }

  public function hasArrangementdeltagelses()
  {
    $has = $this->numberOfArrangementdeltagelses() > 0;
    return $has;
  }

  public function indexOfArrangementdeltagelse($aArrangementdeltagelse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->arrangementdeltagelses as $arrangementdeltagelse)
    {
      if ($arrangementdeltagelse->equals($aArrangementdeltagelse))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setStemmemetode($aStemmemetode)
  {
    $wasSet = false;
    if ($aStemmemetode == null)
    {
      return $wasSet;
    }
    
    $existingStemmemetode = $this->stemmemetode;
    $this->stemmemetode = $aStemmemetode;
    if ($existingStemmemetode != null && $existingStemmemetode != $aStemmemetode)
    {
      $existingStemmemetode->removeArrangement($this);
    }
    $this->stemmemetode->addArrangement($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfArrangementdeltagelses()
  {
    return 0;
  }

  public function addArrangementdeltagelseVia($aProsjekt)
  {
    return new Arrangementdeltagelse($aProsjekt, $this);
  }

  public function addArrangementdeltagelse($aArrangementdeltagelse)
  {
    $wasAdded = false;
    if ($this->indexOfArrangementdeltagelse($aArrangementdeltagelse) !== -1) { return false; }
    $existingArrangement = $aArrangementdeltagelse->getArrangement();
    $isNewArrangement = $existingArrangement != null && $this !== $existingArrangement;
    if ($isNewArrangement)
    {
      $aArrangementdeltagelse->setArrangement($this);
    }
    else
    {
      $this->arrangementdeltagelses[] = $aArrangementdeltagelse;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeArrangementdeltagelse($aArrangementdeltagelse)
  {
    $wasRemoved = false;
    //Unable to remove aArrangementdeltagelse, as it must always have a arrangement
    if ($this !== $aArrangementdeltagelse->getArrangement())
    {
      unset($this->arrangementdeltagelses[$this->indexOfArrangementdeltagelse($aArrangementdeltagelse)]);
      $this->arrangementdeltagelses = array_values($this->arrangementdeltagelses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addArrangementdeltagelseAt($aArrangementdeltagelse, $index)
  {  
    $wasAdded = false;
    if($this->addArrangementdeltagelse($aArrangementdeltagelse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArrangementdeltagelses()) { $index = $this->numberOfArrangementdeltagelses() - 1; }
      array_splice($this->arrangementdeltagelses, $this->indexOfArrangementdeltagelse($aArrangementdeltagelse), 1);
      array_splice($this->arrangementdeltagelses, $index, 0, array($aArrangementdeltagelse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveArrangementdeltagelseAt($aArrangementdeltagelse, $index)
  {
    $wasAdded = false;
    if($this->indexOfArrangementdeltagelse($aArrangementdeltagelse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArrangementdeltagelses()) { $index = $this->numberOfArrangementdeltagelses() - 1; }
      array_splice($this->arrangementdeltagelses, $this->indexOfArrangementdeltagelse($aArrangementdeltagelse), 1);
      array_splice($this->arrangementdeltagelses, $index, 0, array($aArrangementdeltagelse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addArrangementdeltagelseAt($aArrangementdeltagelse, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderStemmemetode = $this->stemmemetode;
    $this->stemmemetode = null;
    $placeholderStemmemetode->removeArrangement($this);
    foreach ($this->arrangementdeltagelses as $aArrangementdeltagelse)
    {
      $aArrangementdeltagelse->delete();
    }
  }

}
?>