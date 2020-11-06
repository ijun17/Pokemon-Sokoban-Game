package com.zetcode;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class Sound {
   public static void Play(String fileName) {
      try {
         AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
         Clip clip = AudioSystem.getClip();
         clip.stop();
         clip.open(ais);
         clip.start();         
      }
      catch(Exception ex) {
      }
   }
   public static void Loop(String filename) {
      try {
         AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
         Clip clip = AudioSystem.getClip();
         clip.stop();
         clip.open(ais);
         clip.start();
         clip.loop(Clip.LOOP_CONTINUOUSLY);
      }
      catch(Exception ex) {
      }
   }

}