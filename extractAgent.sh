#!/bin/sh

if [ -d "/sharedFiles" ]; then
    echo "/sharedFiles found!"

  if [ -f "/sharedFiles/AppServerAgent.zip" ]; then
      echo "/sharedFiles/AppServerAgent.zip found!"

          mkdir -p /appdynamics
      cp /sharedFiles/AppServerAgent.zip /appdynamics/AppServerAgent.zip
      unzip /appdynamics/AppServerAgent.zip -d /appdynamics/AppServerAgent && rm /appdynamics/AppServerAgent.zip

  else

    echo "/sharedFiles/AppServerAgent.zip not found!"

  fi

else

  echo "/sharedFiles not found!"

fi
