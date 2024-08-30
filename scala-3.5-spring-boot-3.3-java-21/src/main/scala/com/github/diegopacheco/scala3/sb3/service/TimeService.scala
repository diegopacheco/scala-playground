package com.github.diegopacheco.scala3.sb3.service

import org.springframework.stereotype.Service

import java.util.Date

@Service
class TimeService {
  def getTime: Date = new Date()
}
