package com.example.emmy_ibook.activity



import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.widget.SearchView


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration

import android.content.Intent
import android.os.Bundle

import android.view.View

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat



import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.emmy_ibook.R
import com.example.emmy_ibook.adapter.ClickListener
import com.example.emmy_ibook.adapter.NavigationRvAdapter
import com.example.emmy_ibook.adapter.RecyclerTouchListener
import com.example.emmy_ibook.databinding.ActivityHomeScreenBinding

import com.example.emmy_ibook.fragments.BookshelfPage

import com.example.emmy_ibook.fragments.DonateFragment

import com.example.emmy_ibook.fragments.HomeFragment
import com.example.emmy_ibook.model.NavigationItemModel
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.navigateUp
import com.example.emmy_ibook.fragments.BookshelfPage


class MainActivity : AppCompatActivity() {

    private val bookshelfPage = BookshelfPage()
    private var homeFragment = HomeFragment()
    private var bookshelfPage = BookshelfPage()
    private lateinit var bottomNavBar : BottomNavigationView


import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.navigateUp
import com.example.emmy_ibook.databinding.ActivitySearchBinding



class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavBar: BottomNavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var adapter: NavigationRvAdapter
    private lateinit var items: ArrayList<NavigationItemModel>

    private lateinit var binding: ActivityHomeScreenBinding
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_book_shelf)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container ) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)
       // binding.navView.setupWithNavController(navController)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)


        //function to replace fragments

        replaceFragment(homeFragment)


        bottomNavBar = binding.bottomNavigation
        bottomNavBar.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_bookshelf -> replaceFragment(bookshelfPage)
               // R.id.ic_notification -> replaceFragment(Not)
            }
            true
        }


        //setCurrentFragment(homeFragment)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.splashScreen2){
//                binding.bottomNavigation.visibility = View.GONE
//            } else{
//                binding.bottomNavigation.visibility = View.VISIBLE
//            }
//        }
//
//        bottomNavBar.setupWithNavController(navController)


        replaceFragment(bookshelfPage)

        bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_bookshelf -> replaceFragment(bookshelfPage)
               // R.id.ic_home -> replaceFragment(notification)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.commit()
    }


    }

    private fun replaceFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }

        //Access fragments upon clicking bottom navigation items
        bottomNavBar = binding.bottomNavigation
        val homeFragment = HomeFragment()

        setCurrentFragment(homeFragment)
        bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> setCurrentFragment(homeFragment)
            }
            true
        }


        items = arrayListOf(
            NavigationItemModel(getString(R.string.donate_a_book)),
            NavigationItemModel(getString(R.string.explore_categories)),
            NavigationItemModel(getString(R.string.profile_details)),
            NavigationItemModel(getString(R.string.search))
        )

        binding.menuImage.setOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }
        binding.searchImage.setOnClickListener {
            navController.navigate(R.id.search)
            updateAdapter(3)
        }
        binding.signOutButton.setOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }


        bottomNavigationView.setupWithNavController(navControllerBottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

        // Setup Recyclerview's Layout
        binding.navigationRv.layoutManager = LinearLayoutManager(this)
        binding.navigationRv.setHasFixedSize(true)

        updateAdapter(0)

        // Add Item Touch Listener
        binding.navigationRv.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        when (position) {
                            0 -> {
                                // # Donate a book
                                navController.navigate(R.id.addNewBook2)
                            }

                            1 -> {
                                // # Explore Categories
                                navController.navigate(R.id.categories)
                            }

                            2 -> {
                                // # Profile Details
                                navController.navigate(R.id.profile)
                            }

                            3 -> {
                                // # Search
                                navController.navigate(R.id.search)
                            }
                        }
                        // Don't highlight the 'Profile' and 'Like us on Facebook' item row
                        if (position != 6 && position != 4) {
                            updateAdapter(position)
                        }
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.drawerLayout.closeDrawer(GravityCompat.START)
                        }, 400)
                    }
                })
        )

    }

    //Private function for hanging up fragments
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }


        bottomNavBar.setupWithNavController(navController)

     }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManger = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.searchMenu)?.actionView as SearchView). apply {
            setSearchableInfo(searchManger.getSearchableInfo(componentName))
        }

        return true

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRvAdapter(items, highlightItemPos)
        binding.navigationRv.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}