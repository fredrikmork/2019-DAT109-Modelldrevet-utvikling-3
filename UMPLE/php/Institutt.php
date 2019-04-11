<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4448.81a70243a modeling language!*/

class Institutt
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Institutt Attributes
  private $name;

  //Institutt Associations
  private $studies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->studies = array();
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

  public function getName()
  {
    return $this->name;
  }

  public function getStudy_index($index)
  {
    $aStudy = $this->studies[$index];
    return $aStudy;
  }

  public function getStudies()
  {
    $newStudies = $this->studies;
    return $newStudies;
  }

  public function numberOfStudies()
  {
    $number = count($this->studies);
    return $number;
  }

  public function hasStudies()
  {
    $has = $this->numberOfStudies() > 0;
    return $has;
  }

  public function indexOfStudy($aStudy)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->studies as $study)
    {
      if ($study->equals($aStudy))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfStudies()
  {
    return 0;
  }

  public function addStudyVia($aName, $aBeskrivelse)
  {
    return new Studie($aName, $aBeskrivelse, $this);
  }

  public function addStudy($aStudy)
  {
    $wasAdded = false;
    if ($this->indexOfStudy($aStudy) !== -1) { return false; }
    $existingInstitutt = $aStudy->getInstitutt();
    $isNewInstitutt = $existingInstitutt != null && $this !== $existingInstitutt;
    if ($isNewInstitutt)
    {
      $aStudy->setInstitutt($this);
    }
    else
    {
      $this->studies[] = $aStudy;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStudy($aStudy)
  {
    $wasRemoved = false;
    //Unable to remove aStudy, as it must always have a institutt
    if ($this !== $aStudy->getInstitutt())
    {
      unset($this->studies[$this->indexOfStudy($aStudy)]);
      $this->studies = array_values($this->studies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStudyAt($aStudy, $index)
  {  
    $wasAdded = false;
    if($this->addStudy($aStudy))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStudies()) { $index = $this->numberOfStudies() - 1; }
      array_splice($this->studies, $this->indexOfStudy($aStudy), 1);
      array_splice($this->studies, $index, 0, array($aStudy));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStudyAt($aStudy, $index)
  {
    $wasAdded = false;
    if($this->indexOfStudy($aStudy) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStudies()) { $index = $this->numberOfStudies() - 1; }
      array_splice($this->studies, $this->indexOfStudy($aStudy), 1);
      array_splice($this->studies, $index, 0, array($aStudy));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStudyAt($aStudy, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->studies as $aStudy)
    {
      $aStudy->delete();
    }
  }

}
?>