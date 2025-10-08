package com.example.location

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat

@Composable
fun Display (
    modifier: Modifier,
    helperClass: HelperClass,
    context: Context,
    locationViewModel: LocationViewModel
             ){

//    val request= requestLocationPermission

    val location = locationViewModel.location.value
    val address =location?.let{
        helperClass.reversedGeocodeLocation(location)
    }


    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        {
                permissions->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION]==true&&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION]==true)
            {
                helperClass.requestLocationUpdates(locationViewModel)
            }
            else
            {
                val rationalRequired =
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION)

                            ||

                            ActivityCompat.shouldShowRequestPermissionRationale(
                                context,
                                Manifest.permission.ACCESS_COARSE_LOCATION)


                if (rationalRequired){
                    Toast.makeText(context,"Location premission is required for this feature to work !!!",
                        Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"Go to android settings and allow the location permission ",
                        Toast.LENGTH_LONG).show()
                }

            }
        }
    )




    Column (
        modifier.fillMaxSize()
            .padding(8.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       if (location!=null){
           Text("Address : ${location.latitude} ${location.longitude} \n $address",
               modifier
                   .padding(8.dp))
       }else{
           Text("No data available !",
               modifier
                   .padding(8.dp))
       }

        Button(onClick = {

            if (helperClass.hasLocationPermission(context)){
                helperClass.requestLocationUpdates(locationViewModel)
            }
            else{
//               request

                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            }

        },
            ) {Text("View Location")}

    }

}




@Composable
fun requestLocationPermission(context: Context){
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions(),
//        {
//            permissions->
//            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION]==true&&
//                permissions[Manifest.permission.ACCESS_FINE_LOCATION]==true)
//            {
//
//            }
//            else
//            {
//                val rationalRequired =
//                            ActivityCompat.shouldShowRequestPermissionRationale(
//                   context as MainActivity,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//
//                ||
//
//                            ActivityCompat.shouldShowRequestPermissionRationale(
//                context,
//                Manifest.permission.ACCESS_COARSE_LOCATION)
//
//
//                if (rationalRequired){
//                    Toast.makeText(context,"Location premission is required for this feature to work !!!",
//                        Toast.LENGTH_LONG).show()
//                }else{
//                    Toast.makeText(context,"Go to android settings and allow the location permission ",
//                        Toast.LENGTH_LONG).show()
//                }
//
//            }
//        }
//    )

//   requestPermissionLauncher.launch(
//       arrayOf(
//           Manifest.permission.ACCESS_FINE_LOCATION,
//           Manifest.permission.ACCESS_COARSE_LOCATION)
//   )
}





//@Composable
//@Preview(showBackground = true)
//fun previewDisplay(){
//    Display(modifier = Modifier)
//}