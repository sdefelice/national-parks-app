package com.example.nationalparks.data.remote.dto

import com.example.nationalparks.data.model.Park
import com.example.nationalparks.data.model.ParkImage

data class Response(
	val total: String? = null,
	val data: List<DataItem?>? = null,
	val limit: String? = null,
	val start: String? = null
)

data class PhoneNumbersItem(
	val extension: String? = null,
	val phoneNumber: String? = null,
	val description: String? = null,
	val type: String? = null
)

data class AddressesItem(
	val city: String? = null,
	val countryCode: String? = null,
	val postalCode: String? = null,
	val provinceTerritoryCode: String? = null,
	val stateCode: String? = null,
	val type: String? = null,
	val line3: String? = null,
	val line2: String? = null,
	val line1: String? = null
)

data class ExceptionsItem(
	val endDate: String? = null,
	val name: String? = null,
	val exceptionHours: ExceptionHours? = null,
	val startDate: String? = null
)

data class ImagesItem(
	val altText: String? = null,
	val caption: String? = null,
	val credit: String? = null,
	val title: String? = null,
	val url: String? = null
)

data class TopicsItem(
	val name: String? = null,
	val id: String? = null
)

data class EmailAddressesItem(
	val emailAddress: String? = null,
	val description: String? = null
)

data class ExceptionHours(
	val sunday: String? = null,
	val saturday: String? = null,
	val tuesday: String? = null,
	val wednesday: String? = null,
	val thursday: String? = null,
	val friday: String? = null,
	val monday: String? = null
)

data class DataItem(
	val fees: List<Any?>? = null,
	val addresses: List<AddressesItem?>? = null,
	val images: List<ImagesItem?>? = null,
	val topics: List<TopicsItem?>? = null,
	val latitude: String? = null,
	val fullName: String? = null,
	val description: String? = null,
	val weatherInfo: String? = null,
	val entrancePasses: List<Any?>? = null,
	val directionsUrl: String? = null,
	val url: String? = null,
	val states: String? = null,
	val latLong: String? = null,
	val activities: List<ActivitiesItem?>? = null,
	val operatingHours: List<OperatingHoursItem?>? = null,
	val entranceFees: List<Any?>? = null,
	val name: String? = null,
	val directionsInfo: String? = null,
	val id: String? = null,
	val parkCode: String? = null,
	val designation: String? = null,
	val contacts: Contacts? = null,
	val longitude: String? = null
)

data class Contacts(
	val emailAddresses: List<EmailAddressesItem?>? = null,
	val phoneNumbers: List<PhoneNumbersItem?>? = null
)

data class StandardHours(
	val sunday: String? = null,
	val saturday: String? = null,
	val tuesday: String? = null,
	val wednesday: String? = null,
	val thursday: String? = null,
	val friday: String? = null,
	val monday: String? = null
)

data class OperatingHoursItem(
	val standardHours: StandardHours? = null,
	val name: String? = null,
	val description: String? = null,
	val exceptions: List<ExceptionsItem?>? = null
)

data class ActivitiesItem(
	val name: String? = null,
	val id: String? = null
)

fun Response.toParkList(): List<Park> {
	return data!!.map {
		toPark(
			it?.description,
			it?.designation,
			it?.directionsInfo,
			it?.directionsUrl,
			it?.fullName,
			it?.id,
			it?.latitude,
			it?.longitude,
			it?.name,
			it?.parkCode,
			it?.states,
			it?.url,
			it?.weatherInfo,
			it?.activities!!.map {activitiesItem ->
				activitiesItem?.name ?: "Empty"
			},
			it.images!!.map { image ->
				toParkImage(
					image?.credit,
					image?.altText,
					image?.title,
					image?.caption,
					image?.url
				)
			}
		)
	}
}

fun toPark(
	description: String?,
	designation: String?,
	directionsInfo: String?,
	directionsUrl: String?,
	fullName: String?,
	id: String?,
	latitude: String?,
	longitude: String?,
	name: String?,
	parkCode: String?,
	states: String?,
	url: String?,
	weatherInfo: String?,
	activities: List<String>,
	images: List<ParkImage>?
): Park {
	return Park(
		description = description,
		designation = designation,
		directionsInfo = directionsInfo,
		directionsURL = directionsUrl,
		fullName = fullName,
		id = id,
		latitude = latitude,
		longitude = longitude,
		name = name,
		parkCode = parkCode,
		states = states,
		url = url,
		weatherInfo = weatherInfo,
		activities = activities,
		images = images
	)
}

fun toParkImage(
	credit: String?,
	altText: String?,
	title: String?,
	caption: String?,
	url: String?
): ParkImage {
	return ParkImage(
		credit = credit,
		altText = altText,
		title = title,
		caption = caption,
		url = url
	)
}