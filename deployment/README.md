# TLA Sample App Deployment

This README documents the current deployment options and helpful commands.

Deployment is done with Kubernetes (K8s).

## Environments

### Local
The local deployment is just used to test K8s manifests with [minikube](https://minikube.sigs.k8s.io/docs/).

_Note:_ To deploy the whole application locally, you need to enable the ingress addon:

```shell
minikube addons enable ingress
```

#### Access App Locally
The ingress is configured to the domain name `tla-sample-app.contextmapper.org`.

_Hint:_ You have to make sure that you have a local DNS entry (add it to /etc/hosts) that points `tla-sample-app.contextmapper.org`
to the local minikube cluster ip address.

You can get the cluster ip address with `minikube ip`.

Once added to `/etc/hosts` and deployed the app, you can access the TLA API under:

[http://tla-sample-app.contextmapper.org](http://tla-sample-app.contextmapper.org)

### DEV
The DEV environment is deployed to Google Kubernetes Engine (GKE).

**Important**: This is just for teaching purposes. The application is down/offline most of the time. 
If we are up-and-running, this is the link to access the TLA API:

[https://tla-sample-dev.contextmapper.org/api/v1/tlas](https://tla-sample-dev.contextmapper.org/api/v1/tlas)

_Note:_ You need to be connected to our cluster in order to deploy from your local machine with `kubectl`.

#### Useful commands

 * Switch context locally with our CI/CD service user: 

   `kubectl config use-context cm-k8s-cluster-1-ci-cd --namespace=tla-sample-dev`

 * For demo; watch rollout status:

   `kubectl rollout status deployment/tla-sample-backend`

   * Also show:

     * `watch kubectl get pods`
     * `watch kubectl get deploy`
     * `watch kubectl get rs`

#### Connection Setup

Google needs to know where the config for the login is:

`export GOOGLE_APPLICATION_CREDENTIALS=~/.kube/gsa-key.json`
