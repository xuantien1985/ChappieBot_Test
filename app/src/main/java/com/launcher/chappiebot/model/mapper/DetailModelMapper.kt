package com.launcher.chappiebot.model.mapper

//import com.launcher.chappiebot.model.NewsFeedModel
//import com.launcher.chappiebot.ui.newsFeed.NewsFeedListItemViewModel
//import com.launcher.chappiebot.ui.newsFeed.NewsFeedListViewModel
//import com.launcher.domain.domain.model.NewsFeed
import com.launcher.chappiebot.model.DetailModel
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailModule
import com.launcher.chappiebot.ui.newsFeeddetail.NewsFeedDetailViewModel
import com.launcher.domain.model.Detail
import com.launcher.domain.model.IAvatar
import com.launcher.domain.model.IPublisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailModelMapper @Inject constructor()
    : ModelMapper<DetailModel, Detail> {

//    fun transformToViewModel(from: List<Detail>, parent: NewsFeedDetailViewModel): List<NewsFeedDetailViewModel> {
//        return from.asSequence()
//            .map(::toModel)
//            .map { detailModels -> NewsFeedDetailViewModel(detailModels, parent) }
//            .toList()
//    }

    override fun toModel(from: Detail): DetailModel {
//        var avatarHref: String = ""
//        var avatarColor: String = ""
//        var avatarWidth: Int = 0
//        var avatarHeight: Int = 0
//        from.avatar?.let {
//            avatarHref = it.href
//            avatarColor = it.main_color
//            avatarWidth = it.width
//            avatarHeight = it.height
//
//        }
//
//        var thumb1: String? = null
//        var thumb2: String? = null
//        var thumb3: String? = null
//        from.images?.let {
//            thumb1 = it.get(0).href
//
//            if (it.size > 1){
//                thumb2 = it.get(1).href
//            }
//
//            if (it.size > 2){
//                thumb3 = it.get(2).href
//            }
//        }

        return DetailModel(
            id = from.document_id,
            title = from.title,
            description = from.description,
            published_date = from.published_date,
            publisherId = from.publisher.id,
            publisherName = from.publisher.name,
            publisherIcon = from.publisher.icon,
            origin_url = from.origin_url,
            template_type = from.template_type

//                imageUrl2 = imageUrl2!!,
//                imageUrl3 = imageUrl3!!



//                avatar = from.avatar,
//                images = from.images,
//                content = from.content
        )
    }

    override fun toDomain(from: DetailModel): Detail {
        return Detail(
            document_id = from.id,
            title = from.title,
            description = from.description,
            published_date = from.published_date,
            publisher = IPublisher(from.publisherId, from.publisherName, from.publisherIcon),
            origin_url = from.origin_url,
            template_type = from.template_type
        )
    }
}