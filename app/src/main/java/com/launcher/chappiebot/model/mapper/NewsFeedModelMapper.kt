package com.launcher.chappiebot.model.mapper

import com.launcher.chappiebot.model.NewsFeedModel
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListItemViewModel
import com.launcher.chappiebot.ui.newsFeed.NewsFeedListViewModel
import com.launcher.domain.domain.model.NewsFeed
import com.launcher.domain.model.IAvatar
import com.launcher.domain.model.IPublisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsFeedModelMapper @Inject constructor()
    : ModelMapper<NewsFeedModel, NewsFeed> {

    fun transformToViewModel(from: List<NewsFeed>, parent: NewsFeedListViewModel): List<NewsFeedListItemViewModel> {
        return from.asSequence()
                .map(::toModel)
                .map { newsFeedModels -> NewsFeedListItemViewModel(newsFeedModels, parent) }
                .toList()
    }

    override fun toModel(from: NewsFeed): NewsFeedModel {
        var avatarHref: String = ""
        var avatarColor: String = ""
        var avatarWidth: Int = 0
        var avatarHeight: Int = 0
        from.avatar?.let {
            avatarHref = it.href
            avatarColor = it.main_color
            avatarWidth = it.width
            avatarHeight = it.height

        }

        var thumb1: String? = null
        var thumb2: String? = null
        var thumb3: String? = null
        from.images?.let {
            thumb1 = it.get(0).href

            if (it.size > 1){
                thumb2 = it.get(1).href
            }

            if (it.size > 2){
                thumb3 = it.get(2).href
            }
        }

        return NewsFeedModel(
                id = from.document_id,
                title = from.title,
                description = from.description,
                content_type = from.content_type,
                published_date = from.published_date,
                publisherId = from.publisher.id,
                publisherName = from.publisher.name,
                publisherIcon = from.publisher.icon,
                origin_url = from.origin_url,
                avatarHref = avatarHref,
                avatarColor = avatarColor,
                avatarWidth = avatarWidth,
                avatarHeight = avatarHeight,
                thumb1 = thumb1,
                thumb2 = thumb2,
                thumb3 = thumb3
        )
    }

    override fun toDomain(from: NewsFeedModel): NewsFeed {
        var arr: MutableList<IAvatar> = mutableListOf<IAvatar>()
        arr.add(IAvatar(from.thumb1!!, "", 12, 12))
        arr.add(IAvatar(from.thumb2!!, "", 12, 12))
        arr.add(IAvatar(from.thumb3!!, "", 12, 12))
        return NewsFeed(
                document_id = from.id,
                title = from.title,
                description = from.description,
                content_type = from.content_type,
                published_date = from.published_date,
                publisher = IPublisher(from.publisherId, from.publisherName, from.publisherIcon),
                origin_url = from.origin_url,
                avatar = IAvatar(from.avatarHref!!, from.avatarColor!!, from.avatarWidth!!, from.avatarHeight!!),
                images = arr
        )
    }
}