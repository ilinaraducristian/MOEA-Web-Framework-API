package com.ilinaraducristian.moeawebframework.moea

import org.moeaframework.core.Algorithm
import org.moeaframework.core.Problem
import org.moeaframework.core.spi.AlgorithmProvider
import java.io.File
import java.net.MalformedURLException
import java.net.URLClassLoader
import java.util.*

class CustomAlgorithmProvider : AlgorithmProvider() {

  override fun getAlgorithm(name: String?, properties: Properties?, problem: Problem?): Algorithm? {
    val file = File("algorithms/$name.class")
    var algorithm: Algorithm? = null
    if (file.exists())
      try {
        algorithm = URLClassLoader(arrayOf(file.toURI().toURL())).loadClass(name).newInstance() as Algorithm
      } catch (e: MalformedURLException) {
        println("MalformedURLException")
      } catch (e: ClassNotFoundException) {
        println("ClassNotFoundException")
      }
    return algorithm;
  }

}