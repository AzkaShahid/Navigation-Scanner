package com.app.ui.sidenavigation.home

import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.bases.BaseActivity
import com.app.databinding.ActivityMainBinding
import com.app.databinding.MapActivity1Binding
import com.app.models.sn.Department
import com.app.ui.main.MainViewModel
import com.app.ui.sidenavigation.SideNavigationActivity
import kotlin.math.pow
import kotlin.math.sqrt


class MapActivity : BaseActivity<MapActivity1Binding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()
    private lateinit var routeOverlay: RouteOverlay



    override fun initBinding() = MapActivity1Binding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {
//        mViewBinding.btnRoute.setOnClickListener {
//          //  updateRoute()
//
//        }
        mViewBinding.btnRoute.setOnClickListener {
            val fromName = mViewBinding.fromInput.text.toString().trim().lowercase()
            val toName = mViewBinding.toInput.text.toString().trim().lowercase()

            Log.d("MapDebug", "From input: $fromName, To input: $toName")

            Toast.makeText(this, "Distance between departments: 8 meters", Toast.LENGTH_LONG).show()

            val fromBtn = getButtonByDept(fromName)
            val toBtn = getButtonByDept(toName)

            Log.d("MapDebug", "From button: $fromBtn, To button: $toBtn")

            routeOverlay = mViewBinding.routeOverlay

            if (fromBtn != null && toBtn != null) {
                drawRouteBetween(fromBtn, toBtn)
            } else {
                Toast.makeText(this, "Invalid department name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateDistance(from: Department, to: Department): Int {
        return sqrt((to.x - from.x).pow(2) + (to.y - from.y).pow(2)).toInt()
    }
    private fun getButtonByDept(name: String): View? {


        return when (name) {
            "law" -> mViewBinding.lawDepartment
            "economics" -> mViewBinding.economicsDepartment
            "engineering" -> mViewBinding.engineeringDepartment
            "nursing" -> mViewBinding.nursingDepartment
            "agriculture" -> mViewBinding.agricultureDdepartment
            "library" -> mViewBinding.libraryDepartment
            "hospital" -> mViewBinding.hospitalDepartment
            "cinema" -> mViewBinding.cinemaDepartment
            "administrative" -> mViewBinding.administrativeDepartment
            else -> null
        }
    }

    private fun drawRouteBetween(button1: View, button2: View) {
        val start = getCenter(button1)
        val end = getCenter(button2)
        routeOverlay.drawRoute(start, end)
    }

    private fun getCenter(view: View): PointF {
        val viewLocation = IntArray(2)
        val overlayLocation = IntArray(2)

        view.getLocationInWindow(viewLocation)
        mViewBinding.routeOverlay.getLocationInWindow(overlayLocation)

        val relativeX = viewLocation[0] - overlayLocation[0] + view.width / 2f
        val relativeY = viewLocation[1] - overlayLocation[1] + view.height / 2f

        return PointF(relativeX, relativeY)
    }



}