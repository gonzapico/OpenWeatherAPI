package xyz.gonzapico.holvitt

import android.app.Activity

/**
 * Created by gfernandez on 7/5/17.
 */
val Activity.app: HolviApp
  get() = application as HolviApp