package com.remotex.feature.presentation.screens.pullrequestlist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import com.remotex.domain.pullrequestlist.models.PullrequestUserModel
import com.remotex.feature.R

private const val DD_MM_YYYY = "dd/MM/yyyy"

@Composable
fun PullrequestListItem(pullrequestModel: PullrequestModel) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(width = 0.5.dp, color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                uriHandler.openUri(pullrequestModel.htmlUrl)
            }
    ) {
        ListItem(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(150.dp),
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            overlineContent = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = pullrequestModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            headlineContent = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = pullrequestModel.createdAt.format(Locale.current, DD_MM_YYYY),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            supportingContent = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = pullrequestModel.body?: stringResource(R.string.no_description),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        minLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    UserSection(pullrequestModel.user)
                }
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserSection(user: PullrequestUserModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            GlideImage(
                model = user.avatarUrl,
                contentDescription = "Avatar de ${user.login}"
            ) {
                it.diskCacheStrategy(DiskCacheStrategy.DATA)
            }
        }
        Text(
            text = user.login,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PullsListItemPreview() {
    PullrequestListItem(
        pullrequestModel = PullrequestModel(
            title = stringResource(R.string.pull_request_1),
            body = LoremIpsum(15).values.joinToString(" "),
            createdAt = stringResource(R.string.fake_date),
            htmlUrl = "",
            user = PullrequestUserModel(
                login = stringResource(R.string.test_user),
                avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun UserSectionPreview() {
    UserSection(
        PullrequestUserModel(
            login = stringResource(R.string.test_user),
            avatarUrl = "https://avatars.githubusercontent.com/u/26586900?v=4"
        )
    )
}
