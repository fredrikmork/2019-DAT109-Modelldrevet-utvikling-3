cmake_minimum_required(VERSION 3.0)
project(model)

set(CMAKE_CXX_STANDARD 11)
include_directories(${CMAKE_SOURCE_DIR})


set(SOURCE_FILES
model_Model.h
model_Main.cpp
Institutt.h
Institutt.cpp
Studie.h
Studie.cpp
Bedrift.h
Bedrift.cpp
Prosjekt.h
Prosjekt.cpp
Arrangement.h
Arrangement.cpp
Arrangementdeltagelse.h
Arrangementdeltagelse.cpp
Stemme.h
Stemme.cpp
Stemmemetode.h
Stemmemetode.cpp
Brukergruppe.h
Brukergruppe.cpp
Bruker.h
Bruker.cpp)

add_executable(model ${SOURCE_FILES})