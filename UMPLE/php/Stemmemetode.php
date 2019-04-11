<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Stemmemetode
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Stemmemetode Attributes
  private $navn;
  private $parameter;

  //Stemmemetode Associations
  private $arrangements;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aNavn, $aParameter)
  {
    $this->navn = $aNavn;
    $this->parameter = $aParameter;
    $this->arrangements = array();
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

  public function setParameter($aParameter)
  {
    $wasSet = false;
    $this->parameter = $aParameter;
    $wasSet = true;
    return $wasSet;
  }

  public function getNavn()
  {
    return $this->navn;
  }

  public function getParameter()
  {
    return $this->parameter;
  }

  public function getArrangement_index($index)
  {
    $aArrangement = $this->arrangements[$index];
    return $aArrangement;
  }

  public function getArrangements()
  {
    $newArrangements = $this->arrangements;
    return $newArrangements;
  }

  public function numberOfArrangements()
  {
    $number = count($this->arrangements);
    return $number;
  }

  public function hasArrangements()
  {
    $has = $this->numberOfArrangements() > 0;
    return $has;
  }

  public function indexOfArrangement($aArrangement)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->arrangements as $arrangement)
    {
      if ($arrangement->equals($aArrangement))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfArrangements()
  {
    return 0;
  }

  public function addArrangementVia($aNavn, $aBeskrivelse, $aUtgaar)
  {
    return new Arrangement($aNavn, $aBeskrivelse, $aUtgaar, $this);
  }

  public function addArrangement($aArrangement)
  {
    $wasAdded = false;
    if ($this->indexOfArrangement($aArrangement) !== -1) { return false; }
    $existingStemmemetode = $aArrangement->getStemmemetode();
    $isNewStemmemetode = $existingStemmemetode != null && $this !== $existingStemmemetode;
    if ($isNewStemmemetode)
    {
      $aArrangement->setStemmemetode($this);
    }
    else
    {
      $this->arrangements[] = $aArrangement;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeArrangement($aArrangement)
  {
    $wasRemoved = false;
    //Unable to remove aArrangement, as it must always have a stemmemetode
    if ($this !== $aArrangement->getStemmemetode())
    {
      unset($this->arrangements[$this->indexOfArrangement($aArrangement)]);
      $this->arrangements = array_values($this->arrangements);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addArrangementAt($aArrangement, $index)
  {  
    $wasAdded = false;
    if($this->addArrangement($aArrangement))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArrangements()) { $index = $this->numberOfArrangements() - 1; }
      array_splice($this->arrangements, $this->indexOfArrangement($aArrangement), 1);
      array_splice($this->arrangements, $index, 0, array($aArrangement));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveArrangementAt($aArrangement, $index)
  {
    $wasAdded = false;
    if($this->indexOfArrangement($aArrangement) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfArrangements()) { $index = $this->numberOfArrangements() - 1; }
      array_splice($this->arrangements, $this->indexOfArrangement($aArrangement), 1);
      array_splice($this->arrangements, $index, 0, array($aArrangement));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addArrangementAt($aArrangement, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->arrangements as $aArrangement)
    {
      $aArrangement->delete();
    }
  }

}
?>